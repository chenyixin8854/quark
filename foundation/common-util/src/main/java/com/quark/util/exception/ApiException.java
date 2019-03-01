package com.quark.util.exception;

import com.quark.util.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiException extends RuntimeException {

    private ResponseCode responseCode;
    private Integer code;
    private String msg;

    public ApiException(ResponseCode responseCode){
        super(responseCode.getMsg());
        this.responseCode = responseCode;
    }

    public ApiException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
