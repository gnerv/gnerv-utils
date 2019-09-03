package com.gnerv.boot.utils;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Mapper工具类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
public class MapperUtils {

    private MappedStatement buildMappedStatement(MappedStatement ms) {
        MappedStatement.Builder builder =
                new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), ms.getSqlSource(), ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());

        List<ResultMap> resultMaps = ms.getResultMaps();

        List<ResultMap> newResultMaps = new ArrayList<>();

        for (ResultMap resultMap : resultMaps) {
            List<ResultMapping> resultMappings = resultMap.getResultMappings();
            List<ResultMapping> composites = buildComposites(ms, resultMap);

            ResultMapping resultMapping = buildResultMapping(ms, resultMap, composites);

            List lists = new ArrayList(resultMappings);
            lists.add(resultMapping);

            ResultMap newResultMap = new ResultMap.Builder(ms.getConfiguration(), resultMap.getId(), resultMap.getType(), lists).build();

            newResultMaps.add(newResultMap);
        }

        //builder.resultMaps(ms.getResultMaps());
        builder.resultMaps(newResultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    private ResultMapping buildResultMapping(MappedStatement ms, ResultMap resultMap, List<ResultMapping> composites){
        Class<?> type = resultMap.getType();
        EntityExtend entityExtend = EntityExtendBean.BEANS_MAP.get(type);
        ResultMapping resultMapping = new ResultMapping.Builder(
                ms.getConfiguration(),
                entityExtend.property(),
                entityExtend.column(),
                entityExtend.type())
                .nestedQueryId(entityExtend.method())
                .composites(composites)
                .build();

        return resultMapping;
    }

    private List<ResultMapping> buildComposites(MappedStatement ms, ResultMap resultMap){
        Class<?> type = resultMap.getType();
        EntityExtend entityExtend = EntityExtendBean.BEANS_MAP.get(type);
        ResultMapping resultMapping = new ResultMapping.Builder(
                ms.getConfiguration(),
                entityExtend.property(),
                entityExtend.column(),
                entityExtend.type())
                .nestedQueryId(entityExtend.method())
                .build();
        List<ResultMapping> composites = new ArrayList<>();
        composites.add(resultMapping);
        return composites;
    }

}
