package com.zc.managementsystem.user.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface NeedPermission {
    String[] value() default {};
}
