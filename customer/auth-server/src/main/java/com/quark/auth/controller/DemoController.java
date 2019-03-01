package com.quark.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {
//    @Value("${test}")
    String foo;

    @RequestMapping("/hi")
    public String home() {
        return "Hello this is auth! ";
    }

    @RequestMapping("/foo")
    public String home(@RequestParam(value = "name", defaultValue = "auth") String name) {
        log.info("======== hello demo ");
        return "hi " + name + " ,i am from port:" + foo;
    }
}
