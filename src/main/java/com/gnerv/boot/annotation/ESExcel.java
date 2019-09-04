package com.gnerv.boot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * excel 实体对象注解
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/28
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ESExcel {

    int columnIndex() default 0;

    String columnName() default "";
}
