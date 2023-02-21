package com.light.rain.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LightRain
 * @Description: 目录扫描
 * @DateTime: 2022-12-07 17:34
 * @Version：1.0
 **/
public class DirectoryScanUtil {

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 7:17
     * @Param: [path]
     * @Return: void
     * @Description: 遍历目录下的所有文件名称
     */
    public static List<String> getAllFileName(String path) {
        ArrayList<String> list = new ArrayList<>();
        String[] names = new File(path).list();
        if (names != null) {
            list.addAll(List.of(names));
        }
        return list;
    }
}
