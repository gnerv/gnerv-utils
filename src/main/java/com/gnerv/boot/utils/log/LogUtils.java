package com.gnerv.boot.utils.log;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 日志工具类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
public class LogUtils {

    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void info(String msg, Object... tag) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        String logInfo = getLogInfo(stackTraceElement, msg);
        logger.info(logInfo, tag);
    }

    public static void error(String msg, Object... tag) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        String logInfo = getLogInfo(stackTraceElement, msg);
        logger.error(logInfo, tag);
    }

    public static void debug(String msg, Object... tag) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        String logInfo = getLogInfo(stackTraceElement, msg);
        logger.debug(logInfo, tag);
    }

    public static void trace(String msg, Object... tag) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        String logInfo = getLogInfo(stackTraceElement, msg);
        logger.trace(logInfo, tag);
    }

    private static String getLogInfo(StackTraceElement stackTraceElement, String msg) {
        // 获取线程名
        String threadName = Thread.currentThread().getName();
        // 获取线程ID
        long threadId = Thread.currentThread().getId();
        // 获取文件名.即xxx.java
        String fileName = stackTraceElement.getFileName();
        // 获取类名.即包名+类名
        String className = stackTraceElement.getClassName();
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();
        // 获取行号
        int lineNumber = stackTraceElement.getLineNumber();
        LogInfo logInfo = new LogInfo(threadName, threadId, fileName, className, methodName, lineNumber, msg);
        return logInfo.toString();
    }

    public static class LogInfo {
        private String threadName;
        private long threadId;
        private String fileName;
        private String className;
        private String methodName;
        private int lineNumber;
        private String msg;

        public LogInfo() {
        }

        public LogInfo(String threadName, long threadId, String fileName, String className, String methodName, int lineNumber, String msg) {
            this.threadName = threadName;
            this.threadId = threadId;
            this.fileName = fileName;
            this.className = className;
            this.methodName = methodName;
            this.lineNumber = lineNumber;
            this.msg = msg;
        }

        public String getThreadName() {
            return threadName;
        }

        public void setThreadName(String threadName) {
            this.threadName = threadName;
        }

        public long getThreadId() {
            return threadId;
        }

        public void setThreadId(long threadId) {
            this.threadId = threadId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}
