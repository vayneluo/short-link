package me.lattice.shortlink.annotation;

import java.lang.annotation.*;

/**
 * @description: API response handler annotation
 * @author: Lattice
 * @date 2022/6/24 14:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ResponseResult {
}
