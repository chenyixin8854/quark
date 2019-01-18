package com.quark.coures.service;

import org.springframework.stereotype.Service;

@Service
public class UserHystricService implements UserService{
    @Override
    public String queryUserInfo(Long userId) {
        return "auth can not found";
    }
}
