package com.quark.auth.service;

import com.quark.auth.dao.UserMapper;
import com.quark.auth.entity.User;
import com.quark.util.enums.ResponseCode;
import com.quark.util.exception.ApiException;
import com.quark.util.model.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {
    private static final String PREFIX="MSG_";
    private static final Integer EXPIRED=7200;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    public JwtToken phoneAuthenticate(String phone, String authCode) {
        String factAuthCode = (String)redisTemplate.opsForValue().get(PREFIX + phone);
        if (StringUtils.isEmpty(factAuthCode)){
            throw new ApiException(ResponseCode.AUTH_CODE_EXPIREED);
        }
        if (!authCode.equals(factAuthCode)) {
            throw new ApiException(ResponseCode.AUTH_CODE_ERROR);
        }
        User user = Optional.ofNullable(userMapper.selectByPhone(phone)).orElseThrow(() -> new ApiException(ResponseCode.TOKEN_ERROR3));
        redisTemplate.opsForValue().set(PREFIX+user.getId(),user,EXPIRED, TimeUnit.SECONDS);
        JwtToken jwtToken=new JwtToken();
        jwtToken.setUserId(user.getId());
        jwtToken.setMobile(user.getUserPhone());
        jwtToken.setUserName(user.getUserName());
        //TODO 加入权限和角色
        return jwtToken;
    }


}
