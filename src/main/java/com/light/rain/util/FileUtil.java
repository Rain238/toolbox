package com.light.rain.util;

import com.light.rain.exception.FilePathErrorException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author: LightRain
 * @Description: 文件读写/网络文件下载工具
 * </br>
 * 包括文件读写
 * </br>
 * 网络文件下载
 * </br>
 * 接收文件上传
 * @DateTime: 2023-02-22 21:43
 * @Version：1.0
 **/
public class FileUtil {
    /**
     * @param size:字节大小 </br>1048576字节=1MB
     *                  </br>
     *                  公式：1024*1024=1MB
     *                  </br>
     *                  公式：1024*1024*1024=1000MB
     *                  </br>
     * @Author: LightRain
     * @Date: 24/2/2023 上午 1:11
     * @Param: [url, path, size]
     * @Return: boolean
     * @Description: 根据所设置的字节大小来决定是否下载URL内容
     * @see <a href="https://cn.unithelper.com/data-storage/1048576-b-mb/">字节大小具体请查阅：https://cn.unithelper.com/data-storage/1048576-b-mb/</a>
     */
    public static boolean copyURLToFileSize(String url, String path, long size) throws IOException {
        return copyURLToFileSizePrivate(url, path, size);
    }

    /**
     * @param size:字节大小 </br>1048576字节=1MB
     *                  </br>
     *                  公式：1024*1024=1MB
     *                  </br>
     *                  公式：1024*1024*1024=1000MB
     *                  </br>
     * @Author: LightRain
     * @Date: 24/2/2023 上午 1:11
     * @Param: [url, path, size]
     * @Return: boolean
     * @Description: 根据所设置的字节大小来决定是否下载URL内容
     * @see <a href="https://cn.unithelper.com/data-storage/1048576-b-mb/">字节大小具体请查阅：https://cn.unithelper.com/data-storage/1048576-b-mb/</a>
     */
    public static boolean copyURLToFileSize(URL url, File path, long size) throws IOException {
        return copyURLToFileSize(url.getPath(), path.getPath(), size);
    }

    /**
     * @param size:字节大小 </br>1048576字节=1MB
     *                  </br>
     *                  公式：1024*1024=1MB
     *                  </br>
     *                  公式：1024*1024*1024=1000MB
     *                  </br>
     * @Author: LightRain
     * @Date: 24/2/2023 上午 1:11
     * @Param: [url, path, size]
     * @Return: boolean
     * @Description: 根据所设置的字节大小来决定是否下载URL内容
     * @see <a href="https://cn.unithelper.com/data-storage/1048576-b-mb/">字节大小具体请查阅：https://cn.unithelper.com/data-storage/1048576-b-mb/</a>
     */
    private static boolean copyURLToFileSizePrivate(String url, String path, long size) throws IOException {
        if (!path.substring(path.lastIndexOf("/") + 1).contains(".")) {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            path = String.format("%s\\%s", path, fileName);
        }
        if (getURLFileSizePrivate(url) <= size) {
            FileUtils.copyURLToFile(new URL(url), new File(path));
            return true;
        }
        return false;
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 12:25
     * @Param: [url]
     * @Return: int
     * @Description: 获取网络文件的字节大小
     * @see <a href="https://cn.unithelper.com/data-storage/1048576-b-mb/">字节大小请查阅：https://cn.unithelper.com/data-storage/1048576-b-mb/</a>
     */
    public static int getURLFileSize(String url) throws IOException {
        return getURLFileSizePrivate(url);
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 12:25
     * @Param: [url]
     * @Return: int
     * @Description: 获取网络文件的字节大小
     * @see <a href="https://cn.unithelper.com/data-storage/1048576-b-mb/">字节大小请查阅：https://cn.unithelper.com/data-storage/1048576-b-mb/</a>
     */
    private static int getURLFileSizePrivate(String url) throws IOException {
        URL uri = new URL(url);
        URLConnection urlConnection = uri.openConnection();
        urlConnection.connect();
        //返回响应报文头字段Content-Length的值
        return urlConnection.getContentLength();
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 4:54
     * @Param: [url, path]
     * @Return: void
     * @Description: 将URL复制到文件
     */
    public static void copyURLToFile(String url, String path) throws IOException {
        FileUtils.copyURLToFile(new URL(url), new File(path));
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 12:08
     * @Param: [url, path]
     * @Return: void
     * @Description: 将URL复制到文件
     */
    public static void copyURLToFile(URL url, File path) throws IOException {
        FileUtils.copyURLToFile(url, path);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 4:54
     * @Param: [urls, path]
     * @Return: void
     * @Description: 将List中的URL逐一复制到指定文件夹下
     * </br>
     * 文件名及后缀和要复制的URL文件名保持一致
     */
    public static void copyURLToFile(List<String> urls, String path) throws IOException {
        urls.forEach(url -> {
            try {
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                FileUtils.copyURLToFile(new URL(url), new File(String.format("%s\\%s", path, fileName)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 12:10
     * @Param: [urls, path]
     * @Return: void
     * @Description: 将List中的URL逐一复制到指定文件夹下
     * </br>
     * 文件名及后缀和要复制的URL文件名保持一致
     */
    public static void copyURLToFile(List<String> urls, File path) throws IOException {
        copyURLToFile(urls, path.getPath());
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 4:54
     * @Param: [urls, path]
     * @Return: void
     * @Description: 将List中的URL逐一复制到指定文件夹下
     * </br>
     * 文件名及后缀和要复制的URL文件名保持一致
     * </br>
     * connectionTimeoutMillis：连接超时毫秒值
     * </br>
     * readTimeoutMillis：读取超时毫秒值
     */
    public static void copyURLToFile(List<String> urls, String path, int connectionTimeoutMillis, int readTimeoutMillis) throws IOException {
        urls.forEach(url -> {
            try {
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                FileUtils.copyURLToFile(new URL(url), new File(String.format("%s\\%s", path, fileName)), connectionTimeoutMillis, readTimeoutMillis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 12:11
     * @Param: [urls, path]
     * @Return: void
     * @Description: 将List中的URL逐一复制到指定文件夹下
     * </br>
     * 文件名及后缀和要复制的URL文件名保持一致
     * </br>
     * connectionTimeoutMillis：连接超时毫秒值
     * </br>
     * readTimeoutMillis：读取超时毫秒值
     */
    public static void copyURLToFile(List<String> urls, File path, int connectionTimeoutMillis, int readTimeoutMillis) throws IOException {
        copyURLToFile(urls, path.getPath(), connectionTimeoutMillis, readTimeoutMillis);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 4:54
     * @Param: [url, path]
     * @Return: void
     * @Description: 将URL复制到文件
     * </br>
     * connectionTimeoutMillis：连接超时毫秒值
     * </br>
     * readTimeoutMillis：读取超时毫秒值
     */
    public static void copyURLToFile(String url, String path, int connectionTimeoutMillis, int readTimeoutMillis) throws IOException {
        FileUtils.copyURLToFile(new URL(url), new File(path), connectionTimeoutMillis, readTimeoutMillis);
    }

    /**
     * @Author: LightRain
     * @Date: 24/2/2023 上午 12:12
     * @Param: [url, path]
     * @Return: void
     * @Description: 将URL复制到文件
     * </br>
     * connectionTimeoutMillis：连接超时毫秒值
     * </br>
     * readTimeoutMillis：读取超时毫秒值
     */
    public static void copyURLToFile(URL url, File path, int connectionTimeoutMillis, int readTimeoutMillis) throws IOException {
        FileUtils.copyURLToFile(url, path, connectionTimeoutMillis, readTimeoutMillis);
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
    public static String readData(String path) throws IOException {
        return readFileData(path);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 10:33
     * @Param: [path]
     * @Return: java.util.List<java.lang.String>
     * @Description: 逐行读取文件数据并返回数据集合
     */
    public static String readData(File path) throws IOException {
        return readFileData(path);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 10:01
     * @Param: [path]
     * @Return: java.util.List<java.lang.String>
     * @Description: 逐行读取文件数据并返回数据集合
     */
    private static String readFileData(Object path) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toString())));
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str).append(System.lineSeparator());
        }
        br.close();
        return sb.toString();
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, int data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, char[] data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, short data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, long data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, String data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, double data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, float data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, Object data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    public static void writeData(String path, boolean data, Charset charset) throws IOException {
        writeFileData(path, data, charset);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:33
     * @Param: [path, data, charset]
     * @Return: void
     * @Description: 将数据写入到指定位置的文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 每次调用则覆盖之前写入的数据</br>
     * 如需将数据写入至文件末尾处</br>
     * 请在后面跟上一个Boolean参数为true即可
     * @since 17
     */
    private static void writeFileData(String path, Object data, Charset charset) throws IOException {
        writeFileData(path, data, charset, false);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, int data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, char[] data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, short data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, long data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, String data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, double data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, float data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, Object data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    public static void writeData(String path, boolean data, Charset charset, boolean append) throws IOException {
        writeFileData(path, data, charset, append);
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 6:58
     * @Param: [path, data, charset, append]
     * @Return: void
     * @Description: 将数据写入到指定文件内
     * </br>
     * 必须指定一个字符集</br>
     * 可使用以下字符集类的其中之一</br>
     * com.light.rain.util.{@linkplain com.light.rain.util.Charset Charset}.</br>
     * java.nio.charset.{@linkplain java.nio.charset.Charset Charset}.</br>
     * 并可将数据追加写入至文件末尾处</br>
     * @since 17
     */
    private static void writeFileData(String path, Object data, Charset charset, boolean append) throws IOException {
        if (new File(path).isDirectory()) {
            throw new FilePathErrorException("File path cannot be a directory");
        }
        new PrintWriter(new FileWriter(path, charset, append), true).println(data);
    }

    /**
     * @Author: LightRain
     * @Date: 27/2/2023 上午 2:04
     * @Param: [file, storagePath, originalName]
     * @Return: java.lang.String
     * @Description: 将图片或文件上传到指定文件夹内
     * <pre>{@code originalName-为true则保留原文件名称}</pre>
     * @since 17
     */
    public static String uploadImg(MultipartFile file, String storagePath, boolean originalName) throws IOException {
        return uploadImgPrivate(file, storagePath, originalName);
    }

    /**
     * @Author: LightRain
     * @Date: 27/2/2023 上午 2:07
     * @Param: [file, storagePath, originalName]
     * @Return: java.lang.String
     * @Description: 将图片或文件上传到指定文件夹内
     * <pre>{@code originalName-为true则保留原文件名称}</pre>
     * @since 17
     */
    public static String uploadImg(MultipartFile file, File storagePath, boolean originalName) throws IOException {
        return uploadImgPrivate(file, storagePath.getPath(), originalName);
    }

    /**
     * @Author: LightRain
     * @Date: 27/2/2023 上午 2:08
     * @Param: [file, storagePath]
     * @Return: java.lang.String
     * @Description: 将图片或文件上传到指定文件夹内
     * </br>
     * 默认使用UUID重新命名
     * @since 17
     */
    public static String uploadImg(MultipartFile file, String storagePath) throws IOException {
        return uploadImgPrivate(file, storagePath, false);
    }

    /**
     * @Author: LightRain
     * @Date: 27/2/2023 上午 2:08
     * @Param: [file, storagePath]
     * @Return: java.lang.String
     * @Description: 将图片或文件上传到指定文件夹内
     * </br>
     * 默认使用UUID重新命名
     * @since 17
     */
    public static String uploadImg(MultipartFile file, File storagePath) throws IOException {
        return uploadImgPrivate(file, storagePath.getPath(), false);
    }
    /**
     * @Author: LightRain
     * @Date: 27/2/2023 上午 2:11
     * @Param: [file, storagePath, originalName]
     * @Return: java.lang.String
     * @Description: 将图片或文件上传到指定文件夹内
     * <pre>{@code originalName-为true则保留原文件名称}</pre>
     * @since 17
     */
    @SuppressWarnings("all")
    private static String uploadImgPrivate(MultipartFile file, String storagePath, boolean originalName) throws IOException {
        if (!new File(storagePath).exists()) {
            new File(storagePath).mkdirs();
        }
        if (originalName) {
            String originalFilename = file.getOriginalFilename();
            file.transferTo(new File(String.format("%s\\%s", storagePath, originalFilename)));
            return originalFilename;
        }
        String fastUUID = UniqueIdUtil.getFastSimpleUUID();
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File(String.format("%s\\%s%s", storagePath, fastUUID, suffix)));
        return fastUUID + suffix;
    }

}
