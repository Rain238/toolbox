import com.light.rain.util.Charset;
import com.light.rain.util.FileUtil;
import com.light.rain.util.UniqueIdUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.light.rain.util.Base64Util.*;
import static com.light.rain.util.DateFormatUtil.getTime;
import static com.light.rain.util.DirectoryScanUtil.getAllFileName;
import static com.light.rain.util.Md5Util.*;

/**
 * @Author: LightRain
 * @Description: 工具方法测试专用
 * @DateTime: 2023-02-22 00:38
 * @Version：1.0
 **/
public class JunitTest {

    @Test
    public void Base64UtilTest() {
        int param = 1234567891;
        long param2 = 123456789153L;
        String param3 = "1234567891";
        String encode = encode(param);
        String encode2 = encode(param2);
        String encode3 = encode(param3);
        System.out.println("encode = " + encode);
        System.out.println("encode2 = " + encode2);
        System.out.println("encode3 = " + encode3);

        String decode = decode(encode);
        String decode2 = decode(encode2.getBytes());
        System.out.println("decode = " + decode);
        System.out.println("decode2 = " + decode2);

        int decode3 = decodeInt(encode);
        int decode4 = decodeInt(encode.getBytes());
        System.out.println("decode = " + decode3);
        System.out.println("decode2 = " + decode4);

        long decode5 = decodeLong(encode2);
        long decode6 = decodeLong(encode2.getBytes());
        System.out.println("decode = " + decode5);
        System.out.println("decode2 = " + decode6);
    }

    @Test
    public void DateFormatUtilTest() {
        System.out.println("获取当前时间戳默认24小时格式 = " + getTime(System.currentTimeMillis()));
        System.out.println("获取当前时间戳并指定日期格式 = " + getTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("获取今日日期默认24小时格式 = " + getTime(0));
        System.out.println("获取今日日期并指定日期格式 = " + getTime(0, "yyyy-MM-dd HH:mm:ss"));
        System.out.println("将时间偏移日期转换为时间戳 = " + getTime("2022-11-25T18:45:47+00:00"));
        System.out.println("将时间偏移日期转换为时间戳并指定日期格式 = " + getTime("2022-11-25T18:45:47+00:00", "yyyy-MM-dd HH:mm:ss"));
        long l = getTime("2022-11-25T18:45:47+00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(getTime(l, "HH:mm:ss"));
    }

    @Test
    public void DirectoryScanUtilTest() {
        List<String> allFileName = getAllFileName("D:\\Software\\QQ\\3164395730\\FileRecv\\");
        allFileName.forEach(n -> System.out.println("文件名 = " + n));
    }

    @Test
    public void Md5UtilTest() {
        String param = "Hello Md5";
        System.out.println("获取32位大写Md5加密 = " + encrypt32bitUpperCase(param));
        System.out.println("获取32位小写Md5加密 = " + encrypt32bitLowerCase(param));
        System.out.println("获取16位大写Md5加密 = " + encrypt16bitLowerCase(param));
        System.out.println("获取16位小写Md5加密 = " + encrypt16bitLowerCase(param));
        System.out.println("获取16位小写Md5加密 = " + encrypt16bitLowerCase(123));
        System.out.println("获取16位小写Md5加密 = " + encrypt16bitLowerCase(123L));
    }

    @Test
    public void UniqueIdUtilTest() {
        System.out.println("UniqueIdUtil.getFastSimpleUUID() = " + UniqueIdUtil.getFastSimpleUUID());
        System.out.println("UniqueIdUtil.getUUID() = " + UniqueIdUtil.getUUID());
        System.out.println("UniqueIdUtil.getFastUUID() = " + UniqueIdUtil.getFastUUID());
        System.out.println("UniqueIdUtil.getRandomUUID() = " + UniqueIdUtil.getRandomUUID());
        System.out.println("UniqueIdUtil.getSnowflakesId() = " + UniqueIdUtil.getSnowflakesId());
        System.out.println("UniqueIdUtil.getObjectId() = " + UniqueIdUtil.getObjectId());
    }
    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 5:35
     * @Param: []
     * @Return: void
     * @Description: 测试FileUtil文件写入
     */
    @Test
    public void FileUtilTest() throws IOException {
        System.out.println("FileUtil = " + FileUtil.readData("D:\\项目\\alicia\\alicia\\src\\main\\resources\\url.txt"));
        List<String> list = FileUtil.readData("D:\\项目\\alicia\\alicia\\src\\main\\resources\\url.txt");
        for (String s : list) {
            FileUtil.writeData("D:\\项目\\alicia\\alicia\\src\\main\\resources\\123456.txt",s,true);
        }
    }

    /**
     * @Author: LightRain
     * @Date: 23/2/2023 下午 5:33
     * @Param: []
     * @Return: void
     * @Description: 测试FileUtil网络复制功能
     */
    @Test
    public void FileUtilTest2() throws IOException {
        List<String> list = new LinkedList<>();
        list.add("https://t4.wodetu.cn/2022/10/19/d297b2a8e02ade7642969f6306121748.png");
        list.add("https://cdn-cookieyes.com/client_data/b625465893342370098580f1/audit-table/YMZEyO_0.json");
        FileUtil.copyURLToFile(list,"D:\\项目\\alicia\\alicia\\src\\main\\resources\\16.txt");
        FileUtil.copyURLToFile("https://cdn-cookieyes.com/client_data/b625465893342370098580f1/audit-table/YMZEyO_0.json","D:\\项目\\alicia\\alicia\\src\\main\\resources\\17.txt");
    }

    @Test
    public void FileUtilTest3() throws IOException {
        List<String> list = FileUtil.readData("D:\\项目\\alicia\\alicia\\src\\main\\resources\\url.txt");
        for (String s : list) {
            FileUtil.writeData("D:\\008.txt",s, Charset.UTF_8,true);
        }
    }

    @Test
    public void FileUtilTest4() throws IOException {
        System.out.println("FileUtils.byteCountToDisplaySize(1000) = " + FileUtils.byteCountToDisplaySize((long) (1024*1024*1024)));
        int urlFileSize = FileUtil.getURLFileSize("https://t4.wodetu.cn/2022/10/19/d297b2a8e02ade7642969f6306121748.png");
        System.out.println(" = >>>>>" + FileUtil.copyURLToFileSize("https://t4.wodetu.cn/2022/10/19/d297b2a8e02ade7642969f6306121748.png", "D:\\项目\\alicia\\alicia\\src\\main\\resources\\", 9865536));
        System.out.println("urlFileSize = " + urlFileSize);
    }

}
