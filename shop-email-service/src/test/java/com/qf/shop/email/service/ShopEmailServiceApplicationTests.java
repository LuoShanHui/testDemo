package com.qf.shop.email.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootTest
class ShopEmailServiceApplicationTests {

    @Autowired
    private JavaMailSender sender;
    @Test
    void sendSimpleMailMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("测试邮件主题-LuoShanHui");
        mailMessage.setText("测试邮件内容-LuoShanHui");
        mailMessage.setFrom("xieheike@qq.com");
        mailMessage.setTo("luoshanhui_17@163.com");
        sender.send(mailMessage);
    }

    @Test
    void sendMimeMessage () throws MessagingException {
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
        mimeMessageHelper.setSubject("测试邮件,带Html格式");
        mimeMessageHelper.setText("点击链接<a href='http://www.baidu.com'>" +
                "http://www.baidu.com</a>",true);
        mimeMessageHelper.setFrom("xieheike@qq.com");
        mimeMessageHelper.setTo("luoshanhui_17@163.com");
        sender.send(message);
    }
}
