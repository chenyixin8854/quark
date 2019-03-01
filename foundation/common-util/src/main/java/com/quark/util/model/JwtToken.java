package com.quark.util.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * token负载信息
 */
@Data
public class JwtToken implements Serializable {
    private Integer userId;
    private String userName;
    private String mobile;
    private List<String> roles;
    private List<String> permissions;
}
