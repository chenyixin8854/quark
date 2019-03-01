package com.quark.auth.filter;

import com.quark.auth.entity.User;
import com.quark.auth.service.JwtService;
import com.quark.auth.service.UserService;
import com.quark.util.annotation.Auth;
import com.quark.util.annotation.AuthIgnore;
import com.quark.util.enums.ResponseCode;
import com.quark.util.exception.ApiException;
import com.quark.util.model.JwtToken;
import com.quark.util.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 登录权限校验
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final String PREFIX="USER_";
    @Value("${jwt.key}")
    private String key;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(AuthIgnore.class)) {
            return true;
        }
        // 执行认证
        if (token == null) {
            throw new ApiException(ResponseCode.TOKEN_ERROR1);
        }

        // 获取 token信息
        JwtToken jwtToken= JwtUtil.verifierToken(token,key).orElseThrow(() ->new ApiException(ResponseCode.TOKEN_ERROR2));
        User user = userService.getUserById(jwtToken.getUserId()).orElseThrow(() -> new ApiException(ResponseCode.TOKEN_ERROR3));

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(Auth.class)) {
            String[] roles=method.getAnnotation(Auth.class).hasRoles();
            List<String> needRoleNames = Arrays.asList(roles);
            List<String> roleNames=userService.getUserRoleNameList(jwtToken.getUserId());
            if (needRoleNames.size()>0){
                return true;
            }else{
                return false;
            }
        }
        return  true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}