package com.gnerv.boot.utils.uuid;

import java.util.UUID;

/**
 * <p>
 * UUID工具类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
public class UUIDUtils {

    /**
     * 去中横线 小写
     *
     * @return uuid
     */
    public static String getLower() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 去中横线 大写
     *
     * @return uuid
     */
    public static String getUpper() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
