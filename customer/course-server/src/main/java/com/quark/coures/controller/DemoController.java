package com.quark.coures.controller;

import com.quark.coures.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {
    @Value("${server.port}")
    String port;
    @Autowired(required = false)
    UserService userService;


    @RequestMapping("/hi")
    public String home() {
        return "Hello this is course! ";
    }


    @GetMapping("/user")
    public String getUser(@RequestParam(value = "userId", defaultValue = "1") String userId) {
        log.info("======== hello getUser ");
        return userService.queryUserInfo(1L);
    }
}
