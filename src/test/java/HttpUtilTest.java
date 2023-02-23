import com.alibaba.fastjson2.JSON;
import com.light.rain.example.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;


public class HttpUtilTest {

    @Test
    public void executeHttpRequest() {
        String url = "https://api.kukuqaq.com/jetbrains/server";
        String result = HttpUtil.doGet(url);

        //        List<Map<String, String>> resultList = JSON.parseObject(result, new TypeReference<List<Map<String, Object>>>() {});
        List<HashMap> resultList = JSON.parseArray(result, HashMap.class);
        resultList.forEach(System.out::println);

    }
}
