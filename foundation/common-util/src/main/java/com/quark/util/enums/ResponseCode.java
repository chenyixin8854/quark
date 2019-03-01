package com.quark.util.enums;

public enum ResponseCode {
    SUCCESS(0,"成功"),
    FAIL(1,"失败"),
    INVALID_PARAM(1000,"请求参数缺失或不正确"),
    PARAM_TOO_BIG(1001,"批量查询不得超过10条记录"),
    SERVICE_UNAVAILABLE(5000,"服务异常，请稍后再试"),
    TIMEOUT_UNAVAILABLE(5001,"超时异常"),
    INTERFACE_ERROR(1001,"接口异常"),
    THRID_ERROR(1002,"第三方接口异常"),
    DATA_IS_EMPTY(1003,"暂无数据"),
    AUTH_CODE_ERROR(1004,"生产验证码异常"),
    TOKEN_ERROR1(4000,"无认证信息，请登录"),
    TOKEN_ERROR2(4001,"无效的认证信息，请重新登录"),
    TOKEN_ERROR3(4002,"用户已不存在"),
    TOKEN_ERROR4(4003,"非法的认证信息，请正确登录"),
    TOKEN_ERROR5(4004,"认证信息已过期，请重新登录"),
    TOKEN_ERROR6(4005,"认证信息有误，请重新登录"),
    USER_NONE(4006,"用户不存在或禁用，请联系管理员"),
    USER_UNAUTH(4010,"用户未配置权限，请联系管理员");


    private int code;

    private String msg;

    ResponseCode(int status, String msg){
        this.code = status;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
