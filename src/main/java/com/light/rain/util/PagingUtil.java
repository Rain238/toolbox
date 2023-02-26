package com.light.rain.util;

/**
 * @Author: LightRain
 * @Description: 分页工具
 * @DateTime: 2023-02-25 11:08
 * @Version：1.0
 **/
public class PagingUtil {

    /**
     * @Author: LightRain
     * @Date: 25/2/2023 下午 12:45
     * @Param: [startPage, pageSize, total] 起始页,每页展示条数,数据总条数
     * @Return: java.util.Map<java.lang.String, java.lang.Integer>
     * @Description: 结果会返回一个int类型的数组包含两个元素
     * </br>
     * 第一个元素是分页起始索引
     * </br>
     * 第二个元素是数据总页数
     * @since 17
     */
    public static int[] startAndTotalPage(int startPage, int pageSize, int total) {
        //分页起始索引
        int start = (startPage - 1) * pageSize;
        //根据条数和每页展示数量计算总页码
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        return new int[]{start, totalPage};
    }
}
