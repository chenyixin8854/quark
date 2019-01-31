package com.quark.util.model;

import com.quark.util.enums.ResponseInfoEnum;
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

    public StandardResponse(ResponseInfoEnum infoEnum, Object data){
        this.code=infoEnum.getCode();
        this.msg=infoEnum.getMessage();
        this.data=data;
    }

    public static StandardResponse successResponse(){
        StandardResponse response=new StandardResponse();
        response.code=ResponseInfoEnum.SUCCESS.getCode();
        response.msg=ResponseInfoEnum.SUCCESS.getMessage();
        return response;
    }

    public static StandardResponse successResponse(Object data){
        StandardResponse response=successResponse();
        response.setData(data);
        return response;
    }


    public static StandardResponse errorResponse(){
        StandardResponse response=new StandardResponse();
        response.code=ResponseInfoEnum.ERROR.getCode();
        response.msg=ResponseInfoEnum.ERROR.getMessage();
        return response;
    }

    public static StandardResponse errorResponse(ResponseInfoEnum infoEnum){
        StandardResponse response=new StandardResponse();
        response.code=infoEnum.getCode();
        response.msg=infoEnum.getMessage();
        return response;
    }

}
