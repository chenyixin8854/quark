package com.quark.coures.Controller;

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
    public String home(@RequestParam(value = "name", defaultValue = "course") String name) {
        log.info("======== hello demo ");
        return "hi " + name + " ,i am from port:" + port;
    }

    @GetMapping("/user")
    public String getUser(@RequestParam(value = "userId", defaultValue = "1") String userId) {
        log.info("======== hello getUser ");
        return userService.queryUserInfo(1L);
    }
}
