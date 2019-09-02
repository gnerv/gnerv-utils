package com.gnerv.boot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gnerv LiGen
 * @description 日志工具类
 * @data 2019/5/31
 */
public class LogUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String msg, Object... tag) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        logger.info(msg, tag);
        getLogInfo(stackTraceElement);
    }

    /**
     * 输出日志所包含的信息
     */
    public static String getLogInfo(StackTraceElement stackTraceElement) {
        StringBuilder logInfoStringBuilder = new StringBuilder();
        // 获取线程名
        String threadName = Thread.currentThread().getName();
        // 获取线程ID
        long threadID = Thread.currentThread().getId();
        // 获取文件名.即xxx.java
        String fileName = stackTraceElement.getFileName();
        // 获取类名.即包名+类名
        String className = stackTraceElement.getClassName();
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();
        // 获取生日输出行数
        int lineNumber = stackTraceElement.getLineNumber();
        return logInfoStringBuilder.toString();
    }
}
