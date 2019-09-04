package com.gnerv.boot.interceptor;

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
        List<ResultMap> resultMaps = ms.getResultMaps();
        ResultMap resultMap = resultMaps.get(1);
        List<ResultMapping> resultMappings = resultMap.getResultMappings();


        //ResultMap.Builder builder = new ResultMap.Builder(new Configuration(), );

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println(target);
        return target instanceof ResultSetHandler ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public Object resultExtend(Object proceed){
        if(proceed instanceof Integer){
            return proceed;
        }
        if(proceed instanceof List){
            List list = (List) proceed;
        }
        if(proceed instanceof Map){
            Map map = (Map) proceed;
        }
        return proceed;
    }

}
