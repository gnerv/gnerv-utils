package com.gnerv.boot.listener;

import com.gnerv.boot.annotation.EntityExtend;
import com.gnerv.boot.common.EntityExtendBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * spring 容器内容 监听器
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/30
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        saveEntityExtendBean(contextRefreshedEvent);
    }

    public void saveEntityExtendBean(ContextRefreshedEvent contextRefreshedEvent) {
        Map<Class, EntityExtend> map = new HashMap<>();
        Map<String, Object> beans = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(EntityExtend.class);
        for (Object o : beans.values()) {
            Class<?> clazz = o.getClass();
            EntityExtend entityExtend = clazz.getAnnotation(EntityExtend.class);
            map.put(clazz, entityExtend);
        }
        EntityExtendBean.BEANS_MAP = map;
    }


}
