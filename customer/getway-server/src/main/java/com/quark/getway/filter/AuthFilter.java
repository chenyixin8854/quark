package com.quark.getway.filter;

import com.alibaba.fastjson.JSON;
import com.quark.util.enums.ResponseInfoEnum;
import com.quark.util.model.StandardResponse;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;


/**
 * 统一认证（部分请求需登录）
 */
@Component
public class AuthFilter implements GlobalFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if ("token".equals(token)) {
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        StandardResponse response1=StandardResponse.errorResponse(ResponseInfoEnum.UNCERTIFIED);
        byte[] datas = JSON.toJSONString(response1).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
