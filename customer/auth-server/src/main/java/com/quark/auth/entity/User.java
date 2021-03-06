package com.quark.auth.entity;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Integer id;

    private String userName;

    private String userPhone;

    private Integer userStatus;

    private Date createTime;

    private Date updateTime;
}