package com.quark.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法访问权限注解
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    /**
     * 必须拥有的权限才能访问，配置多个权限时，只要有一个即可访问,
     */
    String[] hasPermissions() default {};

    /**
     * 必须拥有的角色才能访问，配置多个角色时，只要有一个即可访问
     */
    String[] hasRoles() default {};
}
