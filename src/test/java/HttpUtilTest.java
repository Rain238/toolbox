import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.light.rain.example.HttpUtil;
import org.junit.Test;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtilTest {

    @Test
    public void executeHttpRequest() {
        String url = "https://api.kukuqaq.com/jetbrains/server";
        String result = HttpUtil.doGet(url);
//        List<HashMap> poetList = JSONObject.parseArray(result, HashMap.class);
//        List<String> userList= (List<String>) JSONObject.parse(result).getList("k1",String.class);
        List<HashMap> poetList = JSON.parseArray(result, HashMap.class);
        poetList.forEach(System.out::println);
//        List<Map<String, String>> list = JSON.parseObject(result, new TypeReference<List<Map<String, Object>>>() {});
//        list.forEach(System.out::println);
    }
}
