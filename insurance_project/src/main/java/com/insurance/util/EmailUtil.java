package com.insurance.util;

import com.insurance.pojo.Insurance_User;



import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {
    private static String emailck=null;
    public static String emailregister(Insurance_User user) {

        try {
            String account = "yj570672876@163.com";            //替换为发件人账号
            String password = "LMLEJHATBJSHUVJU";                    //替换为发件人账号密码
            String receiveMailAccount = "yj570672876@163.com";//user.getUserCode();    //替换为收件人账号
            // 1. 使用Properties对象封装连接所需的信息
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议
            props.setProperty("mail.smtp.host", "smtp.163.com");    // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
            //获取当前时间毫秒的后四位作为验证码
            Date date=new Date();
            long datetime=date.getTime();
            //保留后六位数字为验证码
            String val=""+datetime;
            emailck=val.substring(val.length()-6,val.length());
            System.out.println("验证码-->"+emailck);

            // 2. 获取Session对象
            Session session = Session.getDefaultInstance(props);
            // 3. 封装Message对象
            MimeMessage message = createMimeMessage(user, session,account, receiveMailAccount);
            // 4. 使用Transport发送邮件
            Transport transport = session.getTransport();
            transport.connect(account, password);
            transport.sendMessage(message, message.getAllRecipients());
            // 5. 关闭连接
            transport.close();
            System.out.println("发送成功！");
            return emailck;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static MimeMessage createMimeMessage(Insurance_User user, Session session, String account, String sendMail)
            throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(account, "福寿保险", "UTF-8"));
        // 3. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(sendMail, user.getUserName()+"用户", "UTF-8"));
        // 4. Subject: 邮件主题
        message.setSubject("验证邮件", "UTF-8");

        // 5. Content: 邮件正文
        message.setContent("尊敬的"+user.getUserName()+"用户你的验证码是："+emailck, "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }
}


