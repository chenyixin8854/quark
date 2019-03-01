package com.quark.util.model;

import com.quark.util.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;


/**
 * Rest接口封装对象
 */
@Setter
@Getter
public class StandardResponse implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    private StandardResponse(){
    }

    public StandardResponse(ResponseCode responseCode, Object data){
        this.code=responseCode.getCode();
        this.msg=responseCode.getMsg();
        this.data=data;
    }

    public static StandardResponse successResponse(){
        StandardResponse response=new StandardResponse();
        response.code=ResponseCode.SUCCESS.getCode();
        response.msg=ResponseCode.SUCCESS.getMsg();
        return response;
    }

    public static StandardResponse successResponse(Object data){
        StandardResponse response=successResponse();
        response.setData(data);
        return response;
    }


    public static StandardResponse errorResponse(){
        StandardResponse response=new StandardResponse();
        response.code=ResponseCode.FAIL.getCode();
        response.msg=ResponseCode.FAIL.getMsg();
        return response;
    }

    public static StandardResponse errorResponse(ResponseCode responseCode){
        StandardResponse response=new StandardResponse();
        response.code=responseCode.getCode();
        response.msg=responseCode.getMsg();
        return response;
    }

}
