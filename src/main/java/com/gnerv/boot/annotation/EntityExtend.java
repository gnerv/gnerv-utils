package com.gnerv.boot.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 扩展类 注解
 * 对于使用mybatis-plus注解与数据库字段对应
 * 并且mapper接口继承了mybatis-plus的baseMapper的扩展类
 * 在不指定对应crud方法时
 * 将尝试使用通用crud方法
 * 否则必须指定对应crud方法
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/9/4
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityExtend {

    // 被注入的对象
    Class clazz();

    // mapper接口类
    Class mapper();

    // 插入方法名
    String insert() default "";

    // 删除方法名
    String delete() default "";

    // 更新方法名
    String update() default "";

    // 查询方法名
    String select() default "";

    // 参数名
    String parameter() default "";

    // 对应主表字段名
    String mColumn();

    // 对应从表字段名
    String sColumn();

}
