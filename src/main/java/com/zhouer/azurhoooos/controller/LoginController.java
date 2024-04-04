package com.zhouer.azurhoooos.controller;

import com.alibaba.fastjson.JSON;
import com.zhouer.azurhoooos.beans.LoginResult;
import com.zhouer.azurhoooos.entity.User;
import com.zhouer.azurhoooos.service.LoginService;
import com.zhouer.azurhoooos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    public UserService userService;
    @Autowired
    public LoginService loginService;

    @GetMapping("/user")
    public List<User> index(){
        return userService.index();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        if (user == null) {
            return "user=null";
        }else{
            LoginResult result=loginService.checkExist(user);
            return JSON.toJSONString(result);
        }
    }

  }
