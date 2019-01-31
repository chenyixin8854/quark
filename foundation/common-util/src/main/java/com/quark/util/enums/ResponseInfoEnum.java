package com.quark.util.enums;

import lombok.Getter;

/**
 * 接口返回信息编码
 */
@Getter
public enum ResponseInfoEnum {
    SUCCESS(1000,"成功"),
    ERROR(2000,"未知错误"),
    PARAM_ERROR(2001,"参数错误"),
    UNCERTIFIED(4000,"未认证"),
    UNAUTHORIZED(4001,"未授权");

    ResponseInfoEnum(int code,String message){
        this.code=code;
        this.message=message;
    }

    private int code;
    private String message;

}
