package com.light.rain.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.function.Supplier;

/**
 * @Author: LightRain
 * @Description: Json处理工具
 * @DateTime: 2023-03-01 12:45
 * @Version：1.0
 **/
public class JsonUtil<T>{

    /**
     * @Author: LightRain
     * @Date: 2/3/2023 上午 11:54
     * @Param: [text, t1]
     * @Return: T
     * @Description: 将json封装到指定对象中
     * </br>
     * 对象中声明的字段要和json属性名保持一致
     * @since 17
     */
    public static <T> T jsonToObject(T t, String text) {
        return jsonToObject(text, t);
    }

    /**
     * @Author: LightRain
     * @Date: 2/3/2023 上午 11:54
     * @Param: [text, t1]
     * @Return: T
     * @Description: 将json封装到指定对象中
     * </br>
     * 对象中声明的字段要和json属性名保持一致
     * @since 17
     */
    public static <T> T jsonToObject(Supplier<T> t, String text) {
        return jsonToObject(text, t.get());
    }

    /**
     * @Author: LightRain
     * @Date: 2/3/2023 上午 11:54
     * @Param: [text, t1]
     * @Return: T
     * @Description: 将json封装到指定对象中
     * </br>
     * 对象中声明的字段要和json属性名保持一致
     * @since 17
     */
    private static <T> T jsonToObject(String text, T t1) {
        try {
            //将字符串转换为Json对象
            JSONObject jsonObject = JSON.parseObject(text);
            //获取转换对象的字节码后再获取该对象的所有字段
            Field[] fields = t1.getClass().getDeclaredFields();
            for (Field field : fields) {
                //去除字段下划线‘_’
                String fieldName = ClassUtil.removeLine(field.getName());
                //通过字段获取Json对应的属性值
                Object jsonValue = jsonObject.get(fieldName);
                //获取字段类型
                Class<?> aClass = t1.getClass().getDeclaredField(field.getName()).getType();
                //通过类类型获取获取对应类型的值
                Object obj = ClassUtil.getClassTypeValue(aClass, jsonValue);
                //拼接字符串获取set方法名
                String methodName = String.format("set%s%s", field.getName().substring(0, 1).toUpperCase(), field.getName().substring(1));
                //获取set声明方法
                Method declaredMethod = t1.getClass().getDeclaredMethod(methodName, aClass);
                //设置属性值
                declaredMethod.invoke(t1, obj);
            }
            return t1;
        } catch (NoSuchFieldException | ParseException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(Class<T> objectClass, String text) {
        return JSONObject.parseObject(text, objectClass);
    }

    public static <T> T parseObject(T t, String text) {
        return (T) JSONObject.parseObject(text, t.getClass());
    }

    public static <T> T parseObject(Supplier<T> t, String text) {
        return (T) JSONObject.parseObject(text, t.get().getClass());
    }
}
