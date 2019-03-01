package com.quark.coures.service;

import com.quark.coures.interceptor.FeignHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth",fallback = UserHystricService.class,configuration = FeignHeaderInterceptor.class)
public interface UserService {
    @GetMapping("/user")
    String queryUserInfo(@RequestParam(value = "userId") Long userId);
}
