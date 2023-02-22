import com.light.rain.util.UniqueIdUtil;
import org.junit.Test;

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

}
