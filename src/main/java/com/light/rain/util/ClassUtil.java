package com.light.rain.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * @Author: LightRain
 * @Description: 通过反射获取GET/SET方法
 * @DateTime: 2023-03-02 00:51
 * @Version：1.0
 **/
public class ClassUtil {

    /**
     * @Author: LightRain
     * @Date: 1/3/2023 下午 5:53
     * @Param: [ob, name] 对象，属性名
     * @Return: java.lang.Object
     * @Description: 根据属性，获取get方法
     * @since 17
     */
    public static Object getGetMethod(Object ob, String name) throws Exception {
        Method[] m = ob.getClass().getMethods();
        for (Method method : m) {
            System.out.println("method = " + method);
            if (("get" + name).equalsIgnoreCase(method.getName())) {
                return method.invoke(ob);
            }
        }
        return null;
    }

    /**
     * @Author: LightRain
     * @Date: 1/3/2023 下午 5:53
     * @Param: [obj, clazz, filedName, typeClass, value]
     * @Return: void
     * @Description: 根据属性，拿到set方法，并把值set到对象中
     * @since 17
     */
    public static void setValue(Object obj, Class<?> clazz, String filedName, Class<?> typeClass, Object value) {
        filedName = removeLine(filedName);
        String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        try {
            Method method = clazz.getDeclaredMethod(methodName, typeClass);
            method.invoke(obj, getClassTypeValue(typeClass, value));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @Author: LightRain
     * @Date: 1/3/2023 下午 5:53
     * @Param: [typeClass, value]
     * @Return: java.lang.Object
     * @Description: 通过class类型获取获取对应类型的值
     * @since 17
     */
    public static Object getClassTypeValue(Class<?> typeClass, Object value) throws ParseException {
        if (typeClass == int.class ||typeClass == Integer.class) {
            if (null == value) {
                return 0;
            }
            return Integer.parseInt(value.toString());
        } else if (typeClass == short.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == byte.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == double.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == long.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == String.class) {
            if (null == value) {
                return "";
            }
            return value;
        } else if (typeClass == boolean.class) {
            if (null == value) {
                return true;
            }
            return value;
        } else if (typeClass == BigDecimal.class) {
            if (null == value) {
                return new BigDecimal(0);
            }
            return new BigDecimal(value + "");
        } else {
            return typeClass.cast(value);
        }
    }

    /**
     * @Author: LightRain
     * @Date: 1/3/2023 下午 5:54
     * @Param: [str]
     * @Return: java.lang.String
     * @Description: 去除下划线‘_’
     * @since 17
     */
    public static String removeLine(String str) {
        if (null != str && str.contains("_")) {
            int i = str.indexOf("_");
            char ch = str.charAt(i + 1);
            char newCh = (ch + "").substring(0, 1).toUpperCase().toCharArray()[0];
            String newStr = str.replace(str.charAt(i + 1), newCh);
            return newStr.replace("_", "");
        }
        return str;
    }
}
