package com.light.rain.util;

import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @Author: LightRain
 * @Description: 获取客户端信息
 * @DateTime: 2022-12-29 16:53
 * @Version：1.0
 **/
public class ClientInfoScanUtil {
    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 12:44
     * @Param: [request]
     * @Return: java.lang.String
     * @Description: 获取浏览器名称
     */
    public static String getBrowserName(HttpServletRequest request) {
        return browserName(request);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 12:45
     * @Param: [request]
     * @Return: java.lang.String
     * @Description: 获取浏览器版本
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        return browserVersion(request);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 12:45
     * @Param: [request]
     * @Return: java.lang.String
     * @Description: 获取客户端操作系统
     */
    public static String getOsName(HttpServletRequest request) {
        return osName(request);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 12:45
     * @Param: [request]
     * @Return: java.lang.String
     * @Description: 获取客户端IP
     */
    public static String getIp(HttpServletRequest request) {
        return ip(request);
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 12:27
     * @Param: [request]
     * @Return: java.lang.String
     * @Description: 获取浏览器名字
     */
    private static String browserName(HttpServletRequest request) {
        return UserAgentUtil.parse(request.getHeader("User-Agent")).getBrowser().toString();
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 12:28
     * @Param: [request]
     * @Return: java.lang.String
     * @Description: 获取浏览器版本
     */
    private static String browserVersion(HttpServletRequest request) {
        return UserAgentUtil.parse(request.getHeader("User-Agent")).getVersion();
    }

    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 12:29
     * @Param: [request]
     * @Return: java.lang.String
     * @Description: 获取操作系统名称
     */
    private static String osName(HttpServletRequest request) {
        return UserAgentUtil.parse(request.getHeader("User-Agent")).getOs().toString();
    }


    /**
     * @Author: LightRain
     * @Date: 22/2/2023 下午 12:38
     * @Param: [request]
     * @Return: java.lang.String
     * @Description: 获取客户端Ip
     */
    private static String ip(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = "127.0.0.1";
        String mac = "0:0:0:0:0:0:0:1";
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ip.equals(ipAddress) || mac.equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                assert inet != null;
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割 "***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
