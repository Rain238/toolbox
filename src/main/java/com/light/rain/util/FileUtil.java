package com.light.rain.util;

import com.light.rain.exception.FilePathErrorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LightRain
 * @Description: 文件读写工具
 * @DateTime: 2023-02-22 21:43
 * @Version：1.0
 **/
public class FileUtil {
    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, int data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, char[] data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, short data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, long data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, String data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, double data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, float data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, Object data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    public static void writeData(String path, boolean data) throws IOException {
        writeFileData(path, data);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:58
     * @Param: [path, data]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     */
    private static void writeFileData(String path, Object data) throws IOException {
        writeFileData(path, data, false);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, int data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, char[] data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, short data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, long data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, String data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, double data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, float data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, Object data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    public static void writeData(String path, boolean data, boolean append) throws IOException {
        writeFileData(path, data, append);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 11:06
     * @Param: [path, data, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 并可将数据追加写入至文件末尾处
     */
    private static void writeFileData(String path, Object data, boolean append) throws IOException {
        if (new File(path).isDirectory()) {
            throw new FilePathErrorException("File path cannot be a directory");
        }
        new PrintWriter(new FileWriter(path, append), true).println(data);
    }


    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:01
     * @Param: [path]
     * @Return: java.util.List<java.lang.String>
     * @Description: 逐行读取文件数据并返回数据集合
     */
    public static List<String> readData(String path) throws IOException {
        return readFileData(path);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:01
     * @Param: [path]
     * @Return: java.util.List<java.lang.String>
     * @Description: 逐行读取文件数据并返回数据集合
     */
    private static List<String> readFileData(String path) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str;
        while (true) {
            str = reader.readLine();
            if (str == null) {
                break;
            }
            list.add(str);
        }
        return list;
    }
}
