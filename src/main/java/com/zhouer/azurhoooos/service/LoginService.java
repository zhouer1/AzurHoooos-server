package com.zhouer.azurhoooos.service;

import com.zhouer.azurhoooos.beans.LoginResult;
import com.zhouer.azurhoooos.entity.User;
import com.zhouer.azurhoooos.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;

    //验证账号密码是否存在
    public LoginResult checkExist(User user){
        LoginResult result=new LoginResult();
        Integer userId=userMapper.selectUserId(user);
        List<Integer> roleId=userMapper.selectRoleId(userId);
        List<Integer> accessId=userMapper.selectAccessId(roleId);
        List<String> access=userMapper.selectAccess(accessId);

        System.out.println(userId);
        System.out.println(roleId);
        System.out.println(accessId);
        System.out.println(access);

        if(userId==null){
            result.setMsg("登录失败");
            result.setSuccess(false);
            result.setStatusCode("40000");
        }else{
            String token=tokenService.createToken(user);
            result.setMsg("登录成功");
            result.setSuccess(true);
            result.setStatusCode("20000");
            result.setToken(token);
            result.setAccess(access);
        }
        return result;
    }
}
