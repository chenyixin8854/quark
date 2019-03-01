package com.quark.auth.controller;


import com.quark.auth.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @GetMapping("/user")
    public User queryUserInfo(@RequestParam Long userId){
        User user= new User();
        user.setId(1);
        user.setUserName("车上那些");
        user.setUserPhone("12324238479");
        return user;
    }
}
