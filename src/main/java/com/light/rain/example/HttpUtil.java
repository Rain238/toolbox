package com.light.rain.example;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName HttpUtils
 * @Description 请求工具
 * @Author msx
 * @Date 2021-08-26 17:40
 * @Version 1.0
 */
public class HttpUtil {

    public static String doGet(String url) {
        return doGet(url, null, null);
    }
    /**
     * @Description get请求，参数k-v形式
     * @Author xxx
     * @Date 2021-08-26 17:41
     * @param url-[请求地址] headMap-[请求头参数] paramMap-[请求参数]
     * @return String 返回结果
     */
    public static String doGet(String url, Map<String, String> headMap, Map<String, String> paramMap) {
        System.out.println(" = = 请求地址 = = > > > > > > " + url);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;
        try {
            //创建uri
            URIBuilder builder = new URIBuilder(url);
            if (paramMap != null && !paramMap.isEmpty()) {
                Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            //添加请求头
            if (headMap != null && !headMap.isEmpty()) {
                Set<Map.Entry<String, String>> entries = headMap.entrySet();
                System.out.println(" = = 请求头 = = > > > > > > ");
                for (Map.Entry<String, String> e : entries) {
                    System.out.println(e.getKey() + ":" + e.getValue());
                    httpGet.addHeader(e.getKey(), e.getValue());
                }
            }

            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            /*if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }*/
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            System.out.println(" = = 请求返回 = = > > > > > > " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(response, httpClient);
        }
        return result;
    }

    /**
     * @Description post请求，参数k-v形式
     * @Author xxx
     * @Date 2021-08-29 21:13
     * @param url-[请求地址] headMap-[请求头参数] paramMap-[请求参数]
     * @return String 返回结果
     */
    public static String doPost(String url, Map<String, String> headMap, Map<String, String> paramMap) {
        System.out.println(" = = 请求地址 = = > > > > > > " + url);
        System.out.println(" = = 请求参数 = = > > > > > > " + paramMap);
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try{
            //创建uri
            URIBuilder builder = new URIBuilder(url);
            if (paramMap != null && !paramMap.isEmpty()) {
                Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = builder.build();
            //创建http请求
            HttpPost httpPost = new HttpPost(uri);
            //添加请求头
            if (headMap != null && !headMap.isEmpty()) {
                System.out.println(" = = 请求头 = = > > > > > > ");
                Set<Map.Entry<String, String>> entries = headMap.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 执行请求
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(" = = 请求返回 = = > > > > > > " + result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            closeResource(response, httpClient);
        }
        return result;
    }

    /**
     * @Description post请求，请求体为JSON格式
     * @Author xxx
     * @Date 2021-08-27 16:31
     * @param url-[请求地址] headMap-[请求头参数] paramMap-[请求参数]
     * @return String 返回结果
     */
    public static String doPost(String url, Map<String, String> header, String jsonParams){
        System.out.println(" = = 请求地址 = = > > > > > > " + url);
        System.out.println(" = = 请求参数 = = > > > > > > " + jsonParams);
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try{
            //创建http请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            //创建请求内容
            StringEntity entity = new StringEntity(jsonParams, "utf-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            // 设置请求头
            if (null != header && !header.isEmpty()) {
                System.out.println(" = = 请求头 = = > > > > > > ");
                Set<Map.Entry<String, String>> entries = header.entrySet();
                for (Map.Entry<String, String> e : entries) {
                    System.out.println(e.getKey() + ":" + e.getValue());
                    httpPost.setHeader(e.getKey(), e.getValue());
                }
            }
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(" = = 请求返回 = = > > > > > > " + result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            closeResource(response, httpClient);
        }
        return result;
    }

    /**
     * @Description 关闭资源
     * @Author xxx
     * @Date 2021/9/8 10:44
     */
    private static void closeResource(Closeable ... resources) {
        System.out.println("> > > > > > > > > > 关闭流资源 > > > > > > > > > >");
        try {
            for (Closeable resource : resources) {
                if (resource != null) {
                    resource.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

