package com.light.rain.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author: LightRain
 * @Description: 邮件发送工具
 * @DateTime: 2023-02-27 02:45
 * @Version：1.0
 **/
public class EmailUtil {
    /**
     * 邮件标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 收件人邮箱账号
     */
    private String recipientAccount;
    /**
     * 发件人邮箱账号
     */
    private String senderAccount;
    /**
     * 发件人邮箱授权码/登录密码
     */
    private String authorizationCode;
    /**
     * 邮箱类型
     */
    private SmtpHost smtpHost;

    public String getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(String recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public SmtpHost getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(SmtpHost smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @Author: LightRain
     * @Date: 28/2/2023 下午 6:45
     * @Param: [title, content]
     * @Return: boolean
     * @Description: 发送邮箱设置
     * <pre>{@code
     * title-邮件标题
     * content-邮件内容
     * }</pre>
     * @since 17
     */
    public boolean sendMail(String title, String content) throws MessagingException {
        return sendMail(title, content, getRecipientAccount(), getSenderAccount(), getAuthorizationCode(), getSmtpHost());
    }

    /**
     * @Author: LightRain
     * @Date: 28/2/2023 下午 6:45
     * @Param: [title, content]
     * @Return: boolean
     * @Description: 执行发送邮箱
     * @since 17
     */
    public boolean sendMail() throws MessagingException {
        return sendMail(getTitle(), getContent(), getRecipientAccount(), getSenderAccount(), getAuthorizationCode(), getSmtpHost());
    }

    /**
     * @Author: LightRain
     * @Date: 27/2/2023 下午 4:00
     * @Param: [title, content, recipientAccount, senderAccount, authorizationCode, smtpHost]
     * @Return: boolean
     * @Description: 发送邮箱设置
     * <pre>{@code
     * title-邮件标题
     * content-邮件内容
     * recipientAccount-收件人账号
     * senderAccount-发件人账号
     * authorizationCode-发件人邮箱授权码/登录密码
     * smtpHost-邮箱服务器地址
     * }</pre>
     * @since 17
     */
    public static boolean sendMail(String title, String content, String recipientAccount, String senderAccount, String authorizationCode, SmtpHost smtpHost) throws MessagingException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", smtpHost.getHost());
            //发件人的账号
            props.put("mail.user", senderAccount);
            //发件人的密码
            props.put("mail.password", authorizationCode);
            //构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            //使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            //创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            //设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);
            //设置收件人
            InternetAddress toAddress = new InternetAddress(recipientAccount);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            //设置邮件标题
            message.setSubject(title);
            //设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            //发送邮件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EmailUtil(String recipientAccount, String senderAccount, String authorizationCode, SmtpHost smtpHost) {
        this.recipientAccount = recipientAccount;
        this.senderAccount = senderAccount;
        this.authorizationCode = authorizationCode;
        this.smtpHost = smtpHost;
    }

    public EmailUtil() {
    }
}
