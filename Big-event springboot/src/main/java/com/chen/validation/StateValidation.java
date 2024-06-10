package com.chen.validation;

import com.chen.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author: Cotors
 * @create 2024-03-16-15:37
 *  注解@State的校验规则
 */
public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     * @param value 要校验的数据
     * @param Context
     * @return 如果返回true，则表示校验通过；返回false则表示校验不通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext Context) {
        //提供校验规则
        if (value == null){
            return false;
        }

        if (value.equals("已发布") || value.equals("草稿")){
            return true;
        }

        return false;
    }
}
