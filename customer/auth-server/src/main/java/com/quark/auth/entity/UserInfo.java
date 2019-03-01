package com.quark.auth.entity;

import lombok.Data;
import java.util.Date;

@Data
public class UserInfo {
    private Integer id;

    private Integer userId;

    private String userEmail;

    private String userCity;

    private Date createTime;

    private Date updateTime;
}