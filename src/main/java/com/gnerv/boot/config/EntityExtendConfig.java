package com.gnerv.boot.config;

import com.gnerv.boot.interceptor.EntityExtendInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 实体扩展配置
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/9/4
 */
@Configuration
public class EntityExtendConfig {

    @Bean
    public EntityExtendInterceptor getEntityExtendInterceptor(){
        return new EntityExtendInterceptor();
    }

}
