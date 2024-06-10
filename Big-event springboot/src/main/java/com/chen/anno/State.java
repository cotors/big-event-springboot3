package com.chen.anno;

import com.chen.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author: Cotors
 * @create 2024-03-16-15:26
 *  自定义注解stat:用于验证属性值是否为"已发布"或"草稿"
 */
@Documented //元注解
@Target({ElementType.FIELD}) //元注解
@Retention(RetentionPolicy.RUNTIME) //元注解
@Constraint(validatedBy = {StateValidation.class}) //指定提供校验规则的类
public @interface State {
    //注解失败后的提示信息
    String message() default "只能是已发布或草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载 获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
