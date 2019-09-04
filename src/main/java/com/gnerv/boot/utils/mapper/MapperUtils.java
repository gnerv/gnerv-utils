package com.gnerv.boot.utils.mapper;

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
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), ms.getSqlSource(), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    private List<ResultMap> buildResultMap(MappedStatement ms) {
        List<ResultMap> resultMaps = ms.getResultMaps();
        ResultMap build = null;
        for (ResultMap resultMap : resultMaps) {
            List<ResultMapping> composites = buildComposites(ms, resultMap);
            List<ResultMapping> resultMappings = buildResultMapping(ms, resultMap, composites);
            build = new ResultMap.Builder(ms.getConfiguration(), resultMap.getId(), resultMap.getType(), resultMappings).build();
        }
        List lists = new ArrayList(resultMaps);
        lists.add(build);
        return resultMaps;
    }

    private List<ResultMapping> buildResultMapping(MappedStatement ms, ResultMap resultMap, List<ResultMapping> composites) {
        List<ResultMapping> resultMappings = resultMap.getResultMappings();
        String id = resultMap.getId();
        ResultMapping resultMapping =
                new ResultMapping.Builder(ms.getConfiguration(), "extend", "id=b_id", List.class)
                        .nestedQueryId("")
                        .composites(composites)
                        .build();
        List lists = new ArrayList(resultMappings);
        lists.add(resultMapping);
        return lists;
    }

    private List<ResultMapping> buildComposites(MappedStatement ms, ResultMap resultMap) {
        List<ResultMapping> resultMappings = resultMap.getResultMappings();
        String id = resultMap.getId();
        ResultMapping resultMapping =
                new ResultMapping.Builder(ms.getConfiguration(), "extend", "id=b_id", Object.class)
                        .nestedQueryId("")
                        .build();
        List lists = new ArrayList(resultMappings);
        lists.add(resultMapping);
        return lists;
    }

}
