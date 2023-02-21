package com.light.rain.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: LightRain
 * @Description: Md5加密工具
 * @DateTime: 2023-02-21 18:07
 * @Version：1.0
 **/
public class Md5Util {
    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:02
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成32位大写Md5
     */
    public static String encrypt32bitUpperCase(String param) {
        return encrypt32bit(param).toUpperCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:02
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成32位小写Md5
     */
    public static String encrypt32bitLowerCase(String param) {
        return encrypt32bit(param).toLowerCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:03
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成16位大写Md5
     */
    public static String encrypt16bitUpperCase(String param) {
        return encrypt32bit(param).substring(8, 24).toUpperCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:03
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成16位小写Md5
     */
    public static String encrypt16bitLowerCase(String param) {
        return encrypt32bit(param).substring(8, 24).toLowerCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:26
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成32位大写Md5
     */
    public static String encrypt32bitUpperCase(int param) {
        return encrypt32bit(param).toUpperCase();
    }


    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:27
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成32位小写Md5
     */
    public static String encrypt32bitLowerCase(int param) {
        return encrypt32bit(param).toLowerCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:28
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成16位大写Md5
     */
    public static String encrypt16bitUpperCase(int param) {
        return encrypt32bit(param).substring(8, 24).toUpperCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:28
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成16位小写Md5
     */
    public static String encrypt16bitLowerCase(int param) {
        return encrypt32bit(param).substring(8, 24).toLowerCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:35
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成32位大写Md5
     */
    public static String encrypt32bitUpperCase(long param) {
        return encrypt32bit(param).toUpperCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:36
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成32位小写Md5
     */
    public static String encrypt32bitLowerCase(long param) {
        return encrypt32bit(param).toLowerCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:36
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成16位大写Md5
     */
    public static String encrypt16bitUpperCase(long param) {
        return encrypt32bit(param).substring(8, 24).toUpperCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 11:36
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: 生成16位小写Md5
     */
    public static String encrypt16bitLowerCase(long param) {
        return encrypt32bit(param).substring(8, 24).toLowerCase();
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 7:05
     * @Param: [str]
     * @Return: java.lang.String
     * @Description: Md5加密生成32位Md5值
     */
    private static String encrypt32bit(Object param) {
        try {
            byte[] md5Bytes = MessageDigest.getInstance("MD5").digest(param.toString().getBytes());
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new NullPointerException("param is null");
        }
    }
}
