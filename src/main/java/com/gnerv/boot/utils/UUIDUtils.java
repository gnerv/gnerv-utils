package com.gnerv.boot.utils;

import java.util.UUID;


public class UUIDUtils {

    /**
     * 去中横线 小写
     *
     * @return uuid
     */
    @Deprecated
    public static String getLowerUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

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
    @Deprecated
    public static String getUpperUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
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
