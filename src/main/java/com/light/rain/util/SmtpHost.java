package com.light.rain.util;

/**
 * @Author: LightRain
 * @Description: 邮件服务器地址
 * @DateTime: 2023-02-28 18:49
 * @Version：1.0
 **/
public enum SmtpHost {
    /**
     * 阿里SMTP邮箱地址(SSL加密端口：465；非加密端口：25）)
     */
    SMTP_ALIYUN("smtp.aliyun.com"),
    /**
     * 谷歌SMTP邮箱地址(SSL加密端口：587)
     */
    SMTP_GMAIL("mail.gmail.com"),
    /**
     * 新郎SMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_SINA("smtp.sina.com.cn"),
    /**
     * TomSMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_TOM("smtp.tom.com"),
    /**
     * 网易SMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_163("smtp.163.com"),
    /**
     * 126-SMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_126("smtp.126.com "),
    /**
     * 雅虎SMTP邮箱地址(SSL加密端口：587)
     */
    SMTP_YAHOO(":smtp.mail.yahoo.com.cn"),
    /**
     * Foxmail-SMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_AOXMAIL("smtp.foxmail.com"),
    /**
     * QQ-SMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_QQ("smtp.qq.com"),
    /**
     * 搜狐SMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_SOHU("smtp.sohu.com"),
    /**
     * HotMail-SMTP邮箱地址(SSL加密端口：587)
     */
    SMTP_LIVE("smtp.live.com"),
    /**
     * 移动139-SMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_139("smtp.139.com"),
    /**
     * 中华网SMTP邮箱地址(SSL加密端口：25)
     */
    SMTP_CHINA("smtp.china.com");
    private final String value;

    SmtpHost(String value) {
        this.value = value;
    }

    public String getHost() {
        return value;
    }
}
