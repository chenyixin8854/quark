package com.quark.account.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Value("${foo}")
    private String foo;

    @RequestMapping("/hi")
    public String home() {
        return "Hello World! "+foo;
    }
}
