package me.lattice.shortlink.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @description: Encapsulated code. Simplify development
 * @see RestController
 * @author: Lattice
 * @date 2022/6/24 14:49
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@RestController
@ResponseResult
public @interface SimpleController {
}
