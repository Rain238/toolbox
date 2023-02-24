package com.light.rain.build;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: LightRain
 * @Description: Lambda链式构建工具
 * @DateTime: 2023-02-24 10:07
 * @Version：1.0
 * </br>
 * 例：Student build = Builder.builder(Student::new).link(Student::setName, "张三").link(Student::setAge, 16).build()
 **/
public class Builder<T> {
    /**
     * 储存调用方指定构造类的构造器
     */
    private final Supplier<T> supplier;
    /**
     * 存储指定类所有需要初始化的类属性
     */
    private final List<Consumer<T>> dInject = new ArrayList<>();

    /**
     * 构造器
     *
     * @param supplier supplier
     */
    private Builder(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 10:48
     * @Param: [supplier]
     * @Return: com.light.rain.build.Builder<T>
     * @Description: 需要提供一个lambda对象实例
     * </br>
     * 例：Dog::new
     */
    public static <T> Builder<T> builder(Supplier<T> supplier) {
        return new Builder<>(supplier);
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 10:52
     * @Param: [consumer, p1]
     * @Return: com.light.rain.build.Builder<T>
     * @Description: consumer:需要提供一个lambda对象属性
     * </br>
     * 例：Dog::setName
     * </br>
     * p1: 当前属性接收的类型参数
     */
    public <P1> Builder<T> link(InjectConsumer<T, P1> consumer, P1 p1) {
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        dInject.add(c);
        return this;
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 10:57
     * @Param: []
     * @Return: T
     * @Description: 构建实例
     */
    public T build() {
        //调用Supplier生成实例
        T instance = supplier.get();
        //调用传入的setter方法完成属性初始化
        dInject.forEach(d -> d.accept(instance));
        //返回构建完成的类实例
        return instance;
    }
}
