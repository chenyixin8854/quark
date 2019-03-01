package com.quark.auth.controller;
import com.quark.auth.entity.User;
import com.quark.auth.service.AuthService;
import com.quark.auth.service.JwtService;
import com.quark.auth.service.MqService;
import com.quark.auth.service.UserService;
import com.quark.util.annotation.AuthIgnore;
import com.quark.util.enums.ResponseCode;
import com.quark.util.exception.ApiException;
import com.quark.util.model.JwtToken;
import com.quark.util.model.StandardResponse;
import com.quark.util.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class LoginController {
    private static final String PREFIX="MSG_";
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    MqService mqService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    JwtService jwtService;


    /**
     * 获取验证码
     * @return
     */
    @GetMapping("/message/code")
    public StandardResponse authCode(@RequestParam String mobile){
        Map<String, Object> result = new HashMap<>();
        try {
            User user=userService.getUserByMobile(mobile).orElseThrow(()-> new ApiException(ResponseCode.USER_NONE));
            if (user.getUserStatus()==1){
                redisTemplate.delete(PREFIX + mobile);
                String identifyCode = RandomUtil.getRandomStringInRange(1000,9999);
                redisTemplate.opsForValue().set(PREFIX + mobile,identifyCode, 300, TimeUnit.SECONDS);
                mqService.sendNewSms(mobile, "您好，系统登录验证码为" + identifyCode + "，5分钟内有效。");
            }else{
                throw new ApiException(ResponseCode.USER_NONE);
            }
            return StandardResponse.successResponse();
        } catch (ApiException e){
            log.info(e.getMessage());
            throw e;
        } catch(Exception e) {
            log.error("生成验证码失败", e);
            throw new ApiException(ResponseCode.AUTH_CODE_ERROR);
        }
    }

    /**
     * 手机号登录，返回jwt
     * @param phone
     * @param authCode
     * @return
     */
    @AuthIgnore
    @PostMapping("/login/phone")
    public Object phoneLogin(@RequestParam String phone,
                             @RequestParam String authCode){
        Map<String,Object> result=new HashMap<>();
        try{
            JwtToken token=authService.phoneAuthenticate(phone,authCode);
            result.put("userId",token.getUserId());
            result.put("userName",token.getUserName());
            result.put("token",jwtService.createToken(token));
            return result;
        }catch (Exception e){
            log.error("手机号登录报错：",e);
            throw new ApiException(ResponseCode.SERVICE_UNAVAILABLE);
        }
    }


    /**
     * 退出登录
     * @return
     */
    @PostMapping("/logout")
    public StandardResponse logout(HttpServletRequest request){
        try {
            String token = request.getHeader("token");
            jwtService.abolishToken(token);
            return StandardResponse.successResponse();
        } catch (ApiException e){
            log.info(e.getMessage());
            throw e;
        } catch(Exception e) {
            log.error("失败", e);
            throw new ApiException(ResponseCode.AUTH_CODE_ERROR);
        }
    }
}
