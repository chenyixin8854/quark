package com.quark.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqService {

    /**
     * 异步发送短信
     * @param mobile
     * @param s
     */
    public void sendNewSms(String mobile, String s) {

    }
}
