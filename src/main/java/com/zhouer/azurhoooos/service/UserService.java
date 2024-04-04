package com.zhouer.azurhoooos.service;

import com.zhouer.azurhoooos.entity.User;
import com.zhouer.azurhoooos.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> index(){
        return userMapper.findAll();
    }

}
