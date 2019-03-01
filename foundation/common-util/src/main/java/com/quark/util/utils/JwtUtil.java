package com.quark.util.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.quark.util.enums.ResponseCode;
import com.quark.util.exception.ApiException;
import com.quark.util.model.JwtToken;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class JwtUtil {
    /**
     * 校验并获取jwtToken
     * @param token
     * @return
     */
    public static Optional<JwtToken> verifierToken(String token, String secretKey) throws ApiException {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            String json=jwtVerifier.verify(token).getAudience().get(0);
            if (json!=null){
                return Optional.ofNullable(JSONObject.parseObject(json,JwtToken.class));
            }
            return Optional.empty();
        }catch (Exception e){
            log.error("校验失败",e);
            throw new ApiException(ResponseCode.TOKEN_ERROR2);
        }
    }

}
