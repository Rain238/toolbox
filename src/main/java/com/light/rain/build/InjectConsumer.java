package com.light.rain.build;

/**
 * @author LightRain
 */
public interface InjectConsumer<T, P1> {
    /**
     * 接收通用数据
     *
     * @param t  s 任意对象
     * @param p1 s 任意类型
     */
    void accept(T t, P1 p1);
}
