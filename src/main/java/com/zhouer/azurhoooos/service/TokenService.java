package com.zhouer.azurhoooos.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zhouer.azurhoooos.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
    public class TokenService {
        private static final long EXPIRE_TIME = 120 * 60 * 1000;
        public  String createToken(User user){
          Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
          String token="";
          token= JWT.create().withAudience(user.getAccount()) // 将 user的账号保存到 token 里面
                  .withExpiresAt(date) //有效期120分钟
                  .sign(Algorithm.HMAC256(user.getPassword())); // 以 password 作为 token 的密钥
          return token;
  }
}
