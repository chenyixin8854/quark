package com.quark.operate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/hi")
    public String home() {
        return "Hello this is operate! ";
    }
}
