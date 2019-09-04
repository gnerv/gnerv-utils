package com.gnerv.boot.interceptor;

import com.gnerv.boot.annotation.EntityExtend;
import com.gnerv.boot.common.EntityExtendBean;
import com.gnerv.boot.utils.mapper.MapperUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class EntityExtendInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        EntityExtend entityExtend = checkEntityExtend(ms);
        if(StringUtils.isEmpty(entityExtend)){
            return invocation.proceed();
        }
        MappedStatement mappedStatement = MapperUtils.buildMappedStatement(ms, entityExtend);
        args[0] = mappedStatement;
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return target instanceof ResultSetHandler ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public EntityExtend checkEntityExtend(MappedStatement ms){
        EntityExtend entityExtend = null;
        List<ResultMap> resultMaps = ms.getResultMaps();
        for (ResultMap resultMap : resultMaps) {
            Class<?> type = resultMap.getType();
            entityExtend = EntityExtendBean.BEANS_MAP.get(type);
        }
        return entityExtend;
    }

}
