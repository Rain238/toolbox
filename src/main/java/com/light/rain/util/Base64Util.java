package com.light.rain.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author: LightRain
 * @Description: Base64加解密处理
 * @DateTime: 2023-02-21 17:40
 * @Version：1.0
 **/
public class Base64Util {
    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 12:02
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: Base64加密
     */
    public static String encode(String param) {
        return base64Encode(param);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 12:02
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: Base64加密
     */
    public static String encode(int param) {
        return base64Encode(param);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 12:02
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: Base64加密
     */
    public static String encode(Long param) {
        return base64Encode(param);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 12:02
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: Base64解密
     */
    public static String decode(String param) {
        return base64Decode(param);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 1:02
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: Base64解密
     */
    public static String decode(byte[] param) {
        return base64Decode(param);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 1:02
     * @Param: [param]
     * @Return: int
     * @Description: Base64解密为int
     */
    public static int decodeInt(String param) {
        return Integer.parseInt(base64Decode(param));
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 1:02
     * @Param: [param]
     * @Return: int
     * @Description: Base64解密为int
     */
    public static int decodeInt(byte[] param) {
        return Integer.parseInt(base64Decode(param));
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 1:02
     * @Param: [param]
     * @Return: long
     * @Description: Base64解密为long
     */
    public static long decodeLong(String param) {
        return Long.parseLong(base64Decode(param));
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 1:02
     * @Param: [param]
     * @Return: long
     * @Description: Base64解密为long
     */
    public static long decodeLong(byte[] param) {
        return Long.parseLong(base64Decode(param));
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 5:42
     * @Param: [param]
     * @Return: java.lang.String
     * @Description: Base64加密
     */
    private static String base64Encode(Object param) {
        return Base64.getEncoder().encodeToString(param.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 5:42
     * @Param: [base]
     * @Return: byte[]
     * @Description: Base64解密
     */
    private static String base64Decode(String base) {
        return new String(Base64.getDecoder().decode(base.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 上午 12:22
     * @Param: [base]
     * @Return: java.lang.String
     * @Description: Base64解密
     */
    private static String base64Decode(byte[] base) {
        return new String(Base64.getDecoder().decode(base));
    }

}
