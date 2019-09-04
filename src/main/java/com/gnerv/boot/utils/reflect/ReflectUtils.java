package com.gnerv.boot.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * 反射工具
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/13
 */
public class ReflectUtils {

    public static Object getObject(Class clazz) {
        Object o = null;
        try {
            o = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static Class getClazz(String classPath) {
        Class c = null;
        try {
            c = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static Class getClazz(String packagePath, String className) {
        String classPath = packagePath + "." + className;
        Class c = null;
        try {
            c = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static Object setAttributeValue(Object o, String fieldName, Object value){
        try {
            Field declaredField = o.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set(o, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static Object getAttributeValue(Object o, String fieldName){
        Field declaredField = getDeclaredField(o, fieldName);
        declaredField.setAccessible(true);
        Object value = null;
        try {
            value = declaredField.get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static Field getField(Object o, String fieldName){
        Field field = null;
        try {
            field = o.getClass().getField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return field;
    }

    public static Field[] getFields(Object o){
        Field[] fields = o.getClass().getFields();
        return fields;
    }

    public static Field getDeclaredField(Object o, String fieldName){
        Field declaredField = null;
        try {
            declaredField = o.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return declaredField;
    }

    public static Field[] getDeclaredFields(Object o, String fieldName){
        Field[] declaredFields = o.getClass().getDeclaredFields();
        return declaredFields;
    }

    public static Method getMethod(Class c, String methodName, Class[] args) {
        Method method = null;
        try {
            method = c.getMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Method getMethod(Class c, String methodName, Object[] args) {
        Class[] clazz = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            clazz[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = c.getMethod(methodName, clazz);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Method getMethod(String classPath, String methodName, Class[] args) {
        Class c = getClazz(classPath);
        Method method = null;
        try {
            method = c.getMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Method getDeclaredMethod(Class c, String methodName, Class[] args) {
        Method method = null;
        try {
            method = c.getDeclaredMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Method getDeclaredMethod(String classPath, String methodName, Class[] args) {
        Class c = getClazz(classPath);
        Method method = null;
        try {
            method = c.getDeclaredMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Object runMethod(Class c, String methodName, Object[] args){
        Method method = getMethod(c, methodName, args);
        Object invoke = null;
        try {
            invoke = method.invoke(c, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }

    public static Object runMethod(Class c, Method method, Object[] args){
        Object invoke = null;
        try {
            invoke = method.invoke(c, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }

}
