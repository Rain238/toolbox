package com.light.rain.util;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: LightRain
 * @Description: Stream常用工具
 * @DateTime: 2023-02-24 11:25
 * @Version：1.0
 **/
public class CollectionUtil {
    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 12:32
     * @Param: [list, keyMapper, valueMapper, mergeFunction]
     * @Return: java.util.Map<K, U>
     * @Description: 将List类型集合转换为Map类型的集合
     * </br>
     * <pre>{@code
     *      例：List<Student> list = new ArrayList<>();
     *         list.add(new Student("张三",16));
     *         list.add(new Student("张三",17));
     *         list.add(new Student("李四",17));
     *         Map<String, Student> map = CollectionUtil.listToMap(list, Student::getName, value -> value, (k1, k2) -> k1);
     * }</pre>
     * 如果集合中存在重复元素箭头函数指向k1则保留第一次添加时的元素
     * @since 17
     */
    public static <T, K, U> Map<K, U> listToMap(List<T> list, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 12:45
     * @Param: [list, keyMapper]
     * @Return: java.util.Map<K, T>
     * @Description: 将List类型集合转换为Map类型的集合
     * </br>
     * <pre>{@code
     *      例：List<Student> list = new ArrayList<>();
     *         list.add(new Student("张三",16));
     *         list.add(new Student("张三",17));
     *         list.add(new Student("李四",17));
     *         Map<String, Student> map = CollectionUtil.listToMap(list, Student::getName);
     * }</pre>
     * 如果集合中存在重复元默认保留第一次添加时的元素
     * @since 17
     */
    public static <T, K> Map<K, T> listToMap(List<T> list, Function<? super T, ? extends K> keyMapper) {
        return list.stream().collect(Collectors.toMap(keyMapper, value -> value, (k1, k2) -> k1));
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 1:00
     * @Param: [set, keyMapper, valueMapper, mergeFunction]
     * @Return: java.util.Map<K, U>
     * @Description: 将Set类型集合转换为Map类型的集合
     * </br>
     * <pre>{@code
     *      例：Set<Student> set = new HashSet<>();
     *         set.add(new Student("张三",16));
     *         set.add(new Student("张三",17));
     *         set.add(new Student("李四",17));
     *         Map<String, Student> map = CollectionUtil.setToMap(list1, Student::getName, value -> value, (k1, k2) -> k1);
     * }</pre>
     * 如果集合中存在重复元素箭头函数指向k1则保留第一次添加时的元素
     * @since 17
     */
    public static <E, K, U> Map<K, U> setToMap(Set<E> set, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
        return set.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 12:45
     * @Param: [set, keyMapper]
     * @Return: java.util.Map<K, E>
     * @Description: 将List类型集合转换为Map类型的集合
     * </br>
     * <pre>{@code
     *      例：Set<Student> set = new HashSet<>();
     *         set.add(new Student("张三",16));
     *         set.add(new Student("张三",17));
     *         set.add(new Student("李四",17));
     *         Map<String, Student> map = CollectionUtil.setToMap(list, Student::getName);
     * }</pre>
     * 如果集合中存在重复元默认保留第一次添加时的元素
     * @since 17
     */
    public static <E, K> Map<K, E> setToMap(Set<E> set, Function<? super E, ? extends K> keyMapper) {
        return set.stream().collect(Collectors.toMap(keyMapper, value -> value, (k1, k2) -> k1));
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 1:19
     * @Param: [list, predicate]
     * @Return: java.util.List<T>
     * @Description: 根据predicate的lambda表达式来过滤List集合中符合条件的元素
     * </br>
     * <pre>{@code
     *     例： List<Student> list = new ArrayList<>();
     *         list.add(new Student("张三", 16));
     *         list.add(new Student("李四", 17));
     *         List<Student> collect = CollectionUtil.filter(list, user -> user.getAge() > 16);
     * }</pre>
     * @since 17
     */
    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 1:22
     * @Param: [set, predicate]
     * @Return: java.util.Set<E>
     * @Description: 根据predicate的lambda表达式来过滤Set集合中符合条件的元素
     * </br>
     * <pre>{@code
     *     例： Set<Student> set = new HashSet<>();
     *         set.add(new Student("张三", 16));
     *         set.add(new Student("李四", 17));
     *         set<Student> collect = CollectionUtil.filter(set, user -> user.getAge() > 16);
     * }</pre>
     * @since 17
     */
    public static <E> Set<E> filter(Set<E> set, Predicate<? super E> predicate) {
        return set.stream().filter(predicate).collect(Collectors.toSet());
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 2:40
     * @Param: [list, classifier]
     * @Return: java.util.Map<K, java.util.List < T>>
     * @Description: 对List集合分组
     * </br>
     * <pre>{@code
     *     例： List<Student> list = new ArrayList<>();
     *         list.add(new Student("张三", 16));
     *         list.add(new Student("张三", 18));
     *         list.add(new Student("李四", 17));
     *         list.add(new Student("李四", 17));
     *         Map<String, List<Student>> collect = CollectionUtil.groupingBy(list,Student::getName);
     * }</pre>
     * @since 17
     */
    public static <T, K> Map<K, List<T>> groupingBy(List<T> list, Function<? super T, ? extends K> classifier) {
        return list.stream().collect(Collectors.groupingBy(classifier));
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 2:40
     * @Param: [set, classifier]
     * @Return: java.util.Map<K, java.util.Set < E>>
     * @Description: 对Set集合分组
     * </br>
     * <pre>{@code
     *     例： Set<Student> set = new HashSet<>();
     *         set.add(new Student("张三", 16));
     *         set.add(new Student("张三", 18));
     *         set.add(new Student("李四", 17));
     *         set.add(new Student("李四", 17));
     *         Map<String, Set<Student>> collect = CollectionUtil.groupingBy(set,Student::getName);
     * }</pre>
     * @since 17
     */
    public static <E, K> Map<K, Set<E>> groupingBy(Set<E> set, Function<? super E, ? extends K> classifier) {
        return set.stream().collect(Collectors.groupingBy(classifier, Collectors.toSet()));
    }

    /**
     * @param keyExtractor-用于提取可比排序键的函数
     * @Author: LightRain
     * @Date: 24/2/2023 下午 5:51
     * @Param: [list, keyExtractor]
     * @Return: java.util.List<T>
     * @Description: 根据List泛型对象的属性来进行升序排列
     * </br>
     * 排序是稳定的对于无序流，没有做出稳定保证。
     * 这是有状态的中间操作
     * </br>
     * @since 17
     */
    public static <T, U extends Comparable<? super U>> List<T> sorted(List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return list.stream().sorted(Comparator.comparing(keyExtractor)).toList();
    }

    /**
     * @param keyExtractor-用于提取可比排序键的函数
     * @Author: LightRain
     * @Date: 24/2/2023 下午 5:51
     * @Param: [set, keyExtractor]
     * @Return: java.util.Set<E>
     * @Description: 根据List泛型对象的属性来进行升序排列
     * </br>
     * 排序是稳定的对于无序流，没有做出稳定保证。
     * 这是有状态的中间操作
     * </br>
     * @since 17
     */
    public static <E, U extends Comparable<? super U>> Set<E> sorted(Set<E> set, Function<? super E, ? extends U> keyExtractor) {
        return set.stream().sorted(Comparator.comparing(keyExtractor)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @param keyExtractor-用于提取可比排序键的函数
     * @Author: LightRain
     * @Date: 24/2/2023 下午 5:51
     * @Param: [list, keyExtractor]
     * @Return: java.util.List<T>
     * @Description: 根据List泛型对象的属性来进行降序排列
     * </br>
     * 排序是稳定的对于无序流，没有做出稳定保证。
     * 这是有状态的中间操作
     * </br>
     * @since 17
     */
    public static <T, U extends Comparable<? super U>> List<T> sortedReversed(List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return list.stream().sorted(Comparator.comparing(keyExtractor).reversed()).toList();
    }

    /**
     * @param keyExtractor-用于提取可比排序键的函数
     * @Author: LightRain
     * @Date: 24/2/2023 下午 5:51
     * @Param: [set, keyExtractor]
     * @Return: java.util.List<T>
     * @Description: 根据List泛型对象的属性来进行降序排列
     * </br>
     * 排序是稳定的对于无序流，没有做出稳定保证。
     * 这是有状态的中间操作
     * </br>
     * @since 17
     */
    public static <E, U extends Comparable<? super U>> Set<E> sortedReversed(Set<E> set, Function<? super E, ? extends U> keyExtractor) {
        return set.stream().sorted(Comparator.comparing(keyExtractor).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 6:27
     * @Param: [list]
     * @Return: java.util.List<T>
     * @Description: 对List集合中的元素去重
     * @since 17
     */
    public static <T> List<T> distinct(List<T> list) {
        return list.stream().distinct().toList();
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 6:36
     * @Param: [list]
     * @Return: java.util.Optional<T>
     * @Description: 取集合中的第一个元素返回
     * </br>
     * <pre>{@code
     *         例：List<String> list = Arrays.asList("A","A", "B", "C");
     *         CollectionUtil.findFirst(list).ifPresent(System.out::println)
     *      }
     * @since 17
     */
    public static <T> Optional<T> findFirst(List<T> list) {
        return list.stream().findFirst();
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 9:56
     * @Param: [list, predicate]
     * @Return: boolean
     * @Description: 检查List集合中是否包含至少一个满足给定谓词的元素
     * </br>
     * <pre>{@code
     *  例：List<String> list = Arrays.asList("A","A", "B", "C");
     *     boolean a = CollectionUtil.anyMatch(list, s -> s.contains("A"));
     *     }
     * </pre>
     * @since 17
     */
    public static <T> boolean anyMatch(List<T> list, Predicate<? super T> predicate) {
        return list.stream().anyMatch(predicate);
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 9:58
     * @Param: [set, predicate]
     * @Return: boolean
     * @Description: 检查Set集合中是否包含至少一个满足给定谓词的元素
     * </br>
     * <pre>{@code
     *  例：Set<String> set = Set.of("A", "B", "C");
     *     boolean a2 = CollectionUtil.anyMatch(set, s -> s.contains("A"));
     * }
     * </pre>
     * @since 17
     */
    public static <E> boolean anyMatch(Set<E> set, Predicate<? super E> predicate) {
        return set.stream().anyMatch(predicate);
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 10:39
     * @Param: [list, predicate]
     * @Return: boolean
     * @Description: 检查List集合中的元素是否所有都满足给定谓词的元素。
     * </br>
     * 如果没有必要，可能不会对所有元素进行谓词评估 确定结果
     * </br>
     * 如果流为空，则true为返回，并且不评估谓词
     * @since 17
     */
    public static <T> boolean allMatch(List<T> list, Predicate<? super T> predicate) {
        return list.stream().allMatch(predicate);
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 下午 10:39
     * @Param: [set, predicate]
     * @Return: boolean
     * @Description: 检查Set集合中的元素是否所有都满足给定谓词的元素。
     * </br>
     * 如果没有必要，可能不会对所有元素进行谓词评估 确定结果
     * </br>
     * 如果流为空，则true为返回，并且不评估谓词
     * @since 17
     */
    public static <E> boolean allMatch(Set<E> set, Predicate<? super E> predicate) {
        return set.stream().allMatch(predicate);
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 12:29
     * @Param: [list]
     * @Return: java.util.List<java.lang.String>
     * @Description: 将List集合中的所有字母转为大写
     * @since 17
     */
    public static List<String> listToUpperCase(List<String> list) {
        return list.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 12:29
     * @Param: [list]
     * @Return: java.util.List<java.lang.String>
     * @Description: 将List集合中的所有字母转为小写
     * @since 17
     */
    public static List<String> listToLowerCase(List<String> list) {
        return list.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 1:14
     * @Param: [identity, accumulator, values]
     * @Return: T
     * @Description: 合并元素求所有元素相加的结果
     * </br>
     * <pre>{@code
     * 例：CollectionUtil.reduce(0,Integer::sum,1,2,3,4,5);
     * }
     * </pre>
     * 非必要不使用
     * @since 17
     */
    @SafeVarargs
    @Deprecated
    public static <T> T reduce(T identity, BinaryOperator<T> accumulator, T... values) {
        return Stream.of(values).reduce(identity, accumulator);
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 1:29
     * @Param: [action, predicate, values]
     * @Return: java.util.List<T>
     * @Description: CollectionUtil.peek(a - > System.out.println ( " 测试用例 ： " + a), c -> c.contains("测试"), "测试方法", "测试Lambda", "属性");
     * </br>
     * 没必要特殊需求不使用
     * @since 17
     */
    @SafeVarargs
    @Deprecated
    public static <T> List<T> peek(Consumer<? super T> action, Predicate<? super T> predicate, T... values) {
        return Stream.of(values).filter(predicate).peek(action).toList();
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 1:38
     * @Param: [list, keyExtractor]
     * @Return: java.util.Optional<T>
     * @Description: 求List集合中的原子元素的最大值
     * </br>
     * <pre>{@code
     *      例：List<Student> list = new ArrayList<>();
     *         list.add(new Student("张三", 16));
     *         list.add(new Student("李四", 17));
     *         Optional<Student> max = CollectionUtil.mix(list, Student::getAge);
     * }</pre>
     * @since 17
     */
    public static <T, U extends Comparable<? super U>> Optional<T> max(List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return list.stream().max(Comparator.comparing(keyExtractor));
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 1:39
     * @Param: [list, keyExtractor]
     * @Return: java.util.Optional<T>
     * @Description: 求List集合中的原子元素的最小值
     * </br>
     * <pre>{@code
     *      例：List<Student> list = new ArrayList<>();
     *         list.add(new Student("张三", 16));
     *         list.add(new Student("李四", 17));
     *         Optional<Student> max = CollectionUtil.min(list, Student::getAge);
     * }</pre>
     * @since 17
     */
    public static <T, U extends Comparable<? super U>> Optional<T> min(List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return list.stream().min(Comparator.comparing(keyExtractor));
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 1:39
     * @Param: [set, keyExtractor]
     * @Return: java.util.Optional<E>
     * @Description: 求Set集合中的原子元素的最大值
     * </br>
     * <pre>{@code
     *      例：Set<Student> set = new HashSet<>();
     *         set.add(new Student("张三", 16));
     *         set.add(new Student("李四", 17));
     *         Optional<Student> max = CollectionUtil.max(set, Student::getAge);
     * }</pre>
     * @since 17
     */
    public static <E, U extends Comparable<? super U>> Optional<E> max(Set<E> set, Function<? super E, ? extends U> keyExtractor) {
        return set.stream().max(Comparator.comparing(keyExtractor));
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 1:40
     * @Param: [set, keyExtractor]
     * @Return: java.util.Optional<E>
     * @Description: 求Set集合中的原子元素的最小值
     * </br>
     * <pre>{@code
     *      例：Set<Student> set = new HashSet<>();
     *         set.add(new Student("张三", 16));
     *         set.add(new Student("李四", 17));
     *         Optional<Student> min = CollectionUtil.min(set, Student::getAge);
     * }</pre>
     * @since 17
     */
    public static <E, U extends Comparable<? super U>> Optional<E> min(Set<E> set, Function<? super E, ? extends U> keyExtractor) {
        return set.stream().min(Comparator.comparing(keyExtractor));
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 1:57
     * @Param: [set, predicate]
     * @Return: long
     * @Description: 根据Lambda表达式判断符合规则的进行计数统计
     * @since 17
     */
    public static <E> long count(Set<E> set, Predicate<? super E> predicate) {
        return set.stream().filter(predicate).count();
    }

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 上午 1:59
     * @Param: [list, predicate]
     * @Return: long
     * @Description: 根据Lambda表达式判断符合规则的进行计数统计
     * @since 17
     */
    public static <E> long count(List<E> list, Predicate<? super E> predicate) {
        return list.stream().filter(predicate).count();
    }

}
