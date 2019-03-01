package com.quark.auth.service;

import com.quark.util.model.JwtToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public JwtToken phoneAuthenticate(String phone, String authCode) {
        return new JwtToken();
    }


}
