package com.quark.auth.service;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.quark.util.model.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JwtService {
    @Autowired
    RedisTemplate redisTemplate;

    private static final String ISSUER="quark.com";
    private static final String SUBJECT="scholar";
    private static final String PREFIX="TOKEN_";
    private static final String SECRETKEY="my_secret";
    private static final Integer EXPIRE=7200;

    /**
     * 生成token
     * @param jwtToken
     * @return
     */
    public String createToken(JwtToken jwtToken) {
        String token="";
        String key=getTokenKey(jwtToken);
        token= JWT.create().withKeyId(key)
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withIssuedAt(new Date())
                .withAudience(JSON.toJSONString(jwtToken))
                .sign(Algorithm.HMAC256(SECRETKEY));
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key,token,EXPIRE, TimeUnit.SECONDS);
        return token;
    }

    /**
     * 删除token
     * @param token
     */
    public void abolishToken(String token) {
        String key=JWT.decode(token).getKeyId();
        redisTemplate.delete(key);
    }

    /**
     * 生产唯一的token key
     * @param jwtToken
     * @return
     */
    private String getTokenKey(JwtToken jwtToken){
        return PREFIX+jwtToken.getUserId();
    }

}
