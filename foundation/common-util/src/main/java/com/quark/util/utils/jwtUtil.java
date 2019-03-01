package com.quark.util.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.quark.util.exception.AuthException;
import com.quark.util.model.JwtToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class jwtUtil {
    /**
     * 校验并获取jwtToken
     * @param token
     * @return
     */
    public JwtToken verifierToken(String token,String secretKey) throws AuthException {
        String json=null;
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            json=jwtVerifier.verify(token).getAudience().get(0);
        }catch (Exception e){
            log.error("roken校验失败",e);
            throw new AuthException("非法的roken");
        }
        if (json!=null){
            return JSONObject.parseObject(json,JwtToken.class);
        }
        return null;
    }

}
