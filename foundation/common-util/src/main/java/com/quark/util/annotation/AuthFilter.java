package com.quark.util.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
@Inherited
public @interface AuthFilter {

}
