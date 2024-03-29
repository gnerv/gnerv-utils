package com.gnerv.boot.utils.mapper;

import com.gnerv.boot.annotation.EntityExtend;
import com.gnerv.boot.common.EntityExtendBean;
import com.gnerv.boot.utils.reflect.ReflectUtils;
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

    public static String EXTEND = "extend";


    public static MappedStatement buildMappedStatement(MappedStatement ms, EntityExtend entityExtend) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), ms.getSqlSource(), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());

        builder.resultMaps(buildResultMap(ms, entityExtend));

        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    public static List<ResultMap> buildResultMap(MappedStatement ms, EntityExtend entityExtend) {
        List<ResultMap> resultMaps = ms.getResultMaps();
        ResultMap build = null;
        for (ResultMap resultMap : resultMaps) {
            List<ResultMapping> composites = buildComposites(ms, resultMap, entityExtend);
            List<ResultMapping> resultMappings = buildResultMappings(ms, resultMap, composites, entityExtend);
            build = new ResultMap.Builder(ms.getConfiguration(), resultMap.getId(), resultMap.getType(), resultMappings).build();
        }
        List lists = new ArrayList(resultMaps);
        lists.add(build);
        return resultMaps;
    }

    public static List<ResultMapping> buildResultMappings(MappedStatement ms, ResultMap resultMap, List<ResultMapping> composites, EntityExtend entityExtend) {
        List<ResultMapping> resultMappings = resultMap.getResultMappings();
        String id = resultMap.getId();
        ResultMapping resultMapping =
                new ResultMapping.Builder(ms.getConfiguration(), EXTEND, entityExtend.parameter() + "=" + entityExtend.mColumn(), List.class)
                        .nestedQueryId(entityExtend.select())
                        .composites(composites)
                        .build();
        List lists = new ArrayList(resultMappings);
        lists.add(resultMapping);
        return lists;
    }

    public static List<ResultMapping> buildComposites(MappedStatement ms, ResultMap resultMap, EntityExtend entityExtend) {
        List<ResultMapping> resultMappings = resultMap.getResultMappings();
        String id = resultMap.getId();
        ResultMapping resultMapping =
                new ResultMapping.Builder(ms.getConfiguration(), entityExtend.parameter(), entityExtend.mColumn(), Object.class)
                        .nestedQueryId("")
                        .build();
        List lists = new ArrayList(resultMappings);
        lists.add(resultMapping);
        return lists;
    }

}
