package com.quark.getway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * getway 默认只支持webflux controller
 */
@RestController
public class DemoController {
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }


    @RequestMapping("/hi")
    public String home() {
        return "Hello this is getway! ";
    }
}
