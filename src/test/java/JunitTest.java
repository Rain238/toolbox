import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.light.rain.build.Builder;
import com.light.rain.example.pojo.Jpg;
import com.light.rain.example.pojo.JsonTest;
import com.light.rain.example.pojo.Page;
import com.light.rain.example.pojo.Student;
import com.light.rain.util.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.light.rain.util.Base64Util.*;
import static com.light.rain.util.DateFormatUtil.*;
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
    public void DateFormatUtilTest() throws ParseException {
        System.out.println("将字符串日期转换为时间戳= " + getTime("2022-11-25 18:45:47"));
        System.out.println("将字符串日期转换为时间戳= " + getTime("2022-11-25 18:45:47", "yyyy-MM-dd HH:mm:ss"));
        System.out.println("获取当前时间戳默认24小时格式 = " + getTime(System.currentTimeMillis()));
        System.out.println("获取当前时间戳并指定日期格式 = " + getTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("获取今日日期默认24小时格式 = " + getTime(0));
        System.out.println("获取今日日期并指定日期格式 = " + getTime(0, "yyyy-MM-dd HH:mm:ss"));
        System.out.println("将时间偏移日期转换为时间戳 = " + isoDateToTimeStamp("2022-11-25T18:45:47+00:00"));
        System.out.println("将时间偏移日期转换为时间戳并指定日期格式 = " + isoDateToTimeStamp("2022-11-25T18:45:47+00:00", "yyyy-MM-dd HH:mm:ss"));
        long l = isoDateToTimeStamp("2022-11-25T18:45:47+00:00", "yyyy-MM-dd HH:mm:ss");
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
        System.out.println(FileUtil.readData("D:\\项目\\alicia\\alicia\\src\\main\\resources\\url.txt"));
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
        FileUtil.copyURLToFile(list, "D:\\项目\\alicia\\alicia\\src\\main\\resources\\16.txt");
        FileUtil.copyURLToFile("https://cdn-cookieyes.com/client_data/b625465893342370098580f1/audit-table/YMZEyO_0.json", "D:\\项目\\alicia\\alicia\\src\\main\\resources\\17.txt");
    }

    @Test
    public void FileUtilTest3() throws IOException {
        System.out.println(FileUtil.readData("D:\\项目\\alicia\\alicia\\src\\main\\resources\\url.txt"));
    }

    @Test
    public void FileUtilTest4() throws IOException {
        System.out.println("FileUtils.byteCountToDisplaySize(1000) = " + FileUtils.byteCountToDisplaySize((long) (1024 * 1024 * 1024)));
        int urlFileSize = FileUtil.getURLFileSize("https://t4.wodetu.cn/2022/10/19/d297b2a8e02ade7642969f6306121748.png");
        System.out.println(" = >>>>>" + FileUtil.copyURLToFileSize("https://t4.wodetu.cn/2022/10/19/d297b2a8e02ade7642969f6306121748.png", "D:\\项目\\alicia\\alicia\\src\\main\\resources\\", 9865536));
        System.out.println("urlFileSize = " + urlFileSize);
    }

    @Test
    public void BuilderTest() {
        Student 张三 = Builder.builder(Student::new).link(Student::setName, "张三").link(Student::setAge, 16).build();
        System.out.println("张三 = " + 张三.getName());
        System.out.println("张三 = " + 张三.getAge());
    }

    @Test
    public void BuilderTest1() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", 16));
        list.add(new Student("张三", 12));
        list.add(new Student("李四", 17));
        Map<String, Student> collect = list.stream().collect(Collectors.toMap(Student::getName, value -> value, (k1, k2) -> k1));
        collect.values().forEach(a -> System.out.println(a.getName() + a.getAge()));
    }

    @Test
    public void MapUtilTest1() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", 16));
        list.add(new Student("张三", 16));
        list.add(new Student("李四", 17));
        Map<String, Student> map = CollectionUtil.listToMap(list, Student::getName);
        map.values().forEach(a -> System.out.println(a.getName() + a.getAge()));

    }

    @Test
    public void MapUtilTest2() {
        Set<Student> set = new HashSet<>();
        set.add(new Student("张三", 18));
        set.add(new Student("张三", 19));
        set.add(new Student("李四", 17));
        Map<String, Student> map = CollectionUtil.setToMap(set, Student::getName, val -> val, (k1, k2) -> k2);
        map.values().forEach(a -> System.out.println(a.getName() + a.getAge()));
    }

    @Test
    public void MapUtilTest3() {
        Set<Student> list = new HashSet<>();
        list.add(new Student("张三", 16));
        list.add(new Student("李四", 17));
        Set<Student> collect = CollectionUtil.filter(list, user -> user.getAge() > 16);
        collect.forEach(a -> System.out.println(a.getName()));
    }

    @Test
    public void MapUtilTest4() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", 16));
        list.add(new Student("张三", 12));
        list.add(new Student("李四", 17));
        Map<String, List<Student>> collect1 = CollectionUtil.groupingBy(list, Student::getName);
        collect1.forEach((k, v) -> {
            System.out.println("k = " + k);
            if (k.contains("张三")) {
                for (Student student : v) {
                    System.out.println("student.getName() = " + student.getName());
                    System.out.println("student.getAge() = " + student.getAge());
                }
            }
        });
        Set<Student> set = new HashSet<>();
        set.add(new Student("张三", 16));
        set.add(new Student("张三", 18));
        set.add(new Student("李四", 17));
        set.add(new Student("李四", 17));
        Map<String, Set<Student>> collect2 = CollectionUtil.groupingBy(set, Student::getName);
        collect2.forEach((k, v) -> {
            System.out.println("k = " + k);
            if (k.contains("张三")) {
                for (Student student : v) {
                    System.out.println("student.getName() = " + student.getName());
                    System.out.println("student.getAge() = " + student.getAge());
                }
            }
        });
    }

    @Test
    public void MapUtilTest5() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", 16));
        list.add(new Student("张三", 12));
        list.add(new Student("李四", 17));
//        List<Student> collect = list.stream().sorted(Comparator.comparing(Student::getAge)).toList();
        List<Student> sorted = CollectionUtil.sorted(list, Student::getAge);
        sorted.forEach(i -> System.out.println(i.getName() + i.getAge()));
    }

    @Test
    public void MapUtilTest6() {
        Set<Student> set = new HashSet<>();
        set.add(new Student("张三", 16));
        set.add(new Student("王五", 12));
        set.add(new Student("李四", 17));
//        List<Student> collect = list.stream().sorted(Comparator.comparing(Student::getAge)).toList();
        Set<Student> sorted = CollectionUtil.sortedReversed(set, Student::getAge);
        sorted.forEach(i -> System.out.println(i.getName() + i.getAge()));
    }

    @Test
    public void MapUtilTest7() {
        List<String> list = Arrays.asList("A", "A", "B", "C");
        List<String> distinct = CollectionUtil.distinct(list);
        distinct.forEach(System.out::println);
    }

    @Test
    public void MapUtilTest8() {
        List<String> list = Arrays.asList("A", "A", "B", "C");
        boolean a = CollectionUtil.anyMatch(list, s -> s.contains("A"));
        System.out.println("a = " + a);
        Set<String> set = Set.of("A", "B", "C");
        boolean a2 = CollectionUtil.anyMatch(set, s -> s.contains("A"));
        boolean b = CollectionUtil.allMatch(list, s -> s.contains("B"));
        System.out.println("b = " + b);
    }

    @Test
    public void MapUtilTest9() {
        Integer reduce = Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> a + b);
        System.out.println("reduce = " + reduce);
        Integer reduce1 = CollectionUtil.reduce(0, Integer::sum, 1, 2, 3, 4, 5);
        System.out.println("reduce1 = " + reduce1);
    }

    @Test
    public void MapUtilTest10() {
        List<String> collect = Stream.of("程序员田螺", "捡田螺的小男孩", "捡瓶子的小男孩").filter(a -> a.contains("田螺")).peek(a -> System.out.println("关注公众号：" + a)).toList();
        System.out.println("collect = " + collect);
        List<String> peek = CollectionUtil.peek(a -> System.out.println("关注公众号：" + a), c -> c.contains("田螺"), "程序员田螺", "捡田螺的小男孩", "捡瓶子的小男孩");
        System.out.println("peek = " + peek);
    }

    @Test
    public void MapUtilTest11() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", 16));
        list.add(new Student("李四", 17));
        Optional<Student> max1 = CollectionUtil.min(list, Student::getAge);
        max1.ifPresent(info -> System.out.println(info.getName() + info.getAge()));
        Set<Student> set = new HashSet<>();
        set.add(new Student("张三", 16));
        set.add(new Student("李四", 17));
        Optional<Student> max2 = CollectionUtil.max(set, Student::getAge);
        max2.ifPresent(info -> System.out.println(info.getName() + info.getAge()));
    }

    @Test
    public void MapUtilTest12() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", 16));
        list.add(new Student("张三", 18));
        list.add(new Student("李四", 17));
        long count = CollectionUtil.count(list, stu -> stu.getAge() > 16);
        System.out.println("count = " + count);
    }

    @Test
    public void MapUtilTest13() {
        Builder.builder(Student::new).link(Student::setName, "张三").link(Student::setAge, 16).build();
        Builder.builder(Page::new).link(Page::setStart, 0).link(Page::setPageSize, 20).build();
    }

    @Test
    public void EmailUtilTest() throws MessagingException {
        boolean b = EmailUtil.sendMail("测试邮件", "测试内容", "3164395730@qq.com", "xxxxx@aliyun.com", "123456", SmtpHost.SMTP_ALIYUN);
        System.out.println("b = " + b);
    }

    @Test
    public void EmailUtilTest2() throws MessagingException {
        EmailUtil e = new EmailUtil();
        e.setTitle("测试邮件");
        e.setContent("测试邮件1");
        e.setRecipientAccount("3164395730@qq.com");
        e.setSenderAccount("xxxxx@aliyun.com");
        e.setAuthorizationCode("123456");
        e.setSmtpHost(SmtpHost.SMTP_ALIYUN);
        boolean b = e.sendMail();
        System.out.println("b = " + b);
    }

    @Test
    public void EmailUtilTest3() throws MessagingException {
        EmailUtil e = new EmailUtil("3164395730@qq.com", "xxxxxx@aliyun.com", "123456", SmtpHost.SMTP_ALIYUN);
        e.setTitle("测试邮件");
        e.setContent("测试邮件1");
        boolean b = e.sendMail();
        System.out.println("b = " + b);
    }

    @Test
    public void EmailUtilTest4() throws MessagingException {
        EmailUtil e = new EmailUtil();
        e.setRecipientAccount("3164395730@qq.com");
        e.setSenderAccount("xxxxx@aliyun.com");
        e.setAuthorizationCode("123456");
        e.setSmtpHost(SmtpHost.SMTP_ALIYUN);
        boolean b = e.sendMail("测试邮件2", "123456");
        System.out.println("b = " + b);
    }

    @Test
    public void JSONUtilTest() {
        String json = "{\"uploadDate\":\"2022-10-27T03:06:07+00:00\",\"userName\":\"弾\",\"title\":\"エルフ放熱中...\",\"userId\":\"339939\"}";
        JsonTest jsonTest = JsonUtil.jsonToObject(new JsonTest(), json);
        System.out.println("getTitle() = " + jsonTest.getTitle());
        System.out.println("getUpload_Date() = " + jsonTest.getUploadDate());
        System.out.println("getUserId() = " + jsonTest.getUserId());
        System.out.println("getUserName() = " + jsonTest.getUserName());
    }

    @Test
    public void JSONUtilTest2() {
        String json = "{\"uploadDate\":\"2022-10-27T03:06:07+00:00\",\"userName\":\"弾\",\"title\":\"エルフ放熱中...\",\"userId\":\"339939\"}";
        JsonTest jsonTest = JsonUtil.jsonToObject(JsonTest::new, json);
        System.out.println("getTitle() = " + jsonTest.getTitle());
        System.out.println("getUpload_Date() = " + jsonTest.getUploadDate());
        System.out.println("getUserId() = " + jsonTest.getUserId());
        System.out.println("getUserName() = " + jsonTest.getUserName());
    }

    @Test
    public void JSONUtilTest3() throws IOException {
        String json = FileUtil.readData("E:\\Mirlkoi\\新建文件夹 (2)\\Crawled\\2022-10-28\\pic-data.json");
        JSONObject jsonObject = JSON.parseObject(json);
        Object data = jsonObject.get("data");
        Object o = JSON.parseObject(data.toString()).get("102277142_p0.jpg");
        System.out.println("o = " + o);
        JsonTest jsonTest = JsonUtil.jsonToObject(JsonTest::new, o.toString());
        System.out.println("jsonTest.getUserName() = " + jsonTest.getUserName());
        System.out.println("jsonTest.getTitle() = " + jsonTest.getTitle());
        System.out.println("jsonTest.getUserId() = " + jsonTest.getUserId());
        System.out.println("jsonTest.getUploadDate() = " + jsonTest.getUploadDate());
    }

    @Test
    public void JSONUtilTest4() throws IOException {
        String json = FileUtil.readData("E:\\Mirlkoi\\新建文件夹 (2)\\Crawled\\2022-10-28\\pic-data.json");
        JSONObject jsonObject = JSON.parseObject(json);
        Object data = jsonObject.get("data");
        Jpg jpg = JSONObject.parseObject(data.toString(), Jpg.class);
        System.out.println("jpg.getJpg().getTitle() = " + jpg.getJpg().getTitle());
    }

    @Test
    public void JSONUtilTest5() throws IOException {
        String json = FileUtil.readData("E:\\Mirlkoi\\新建文件夹 (2)\\Crawled\\2022-10-28\\pic-data.json");
        JSONObject jsonObject = JSON.parseObject(json);
        Object data = jsonObject.get("data");
        Jpg jpg = JsonUtil.parseObject(Jpg::new,data.toString());
        System.out.println("jpg.getJpg().getTitle() = " + jpg.getJpg().getTitle());
    }

}
