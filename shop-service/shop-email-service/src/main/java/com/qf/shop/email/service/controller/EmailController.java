package com.qf.shop.email.service.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${activeServerUrl}")
    private String activeServerUrl;


    //http://localhost:8763/active/account/339934c6-31db-4e8c-a43b-d604f6111cb1
    @RequestMapping("active/account/{uuid}")
    public ResultBean activeAccount(@PathVariable String uuid) {
        //1.组织redis中的已经存好的键
        String redisKey = RedisUtil.getRedisKey(RedisConstant.REGIST_EMAIL_URL_KEY_PRE, uuid);
        //2.从redis中,通过key得到对应的值
        String addr = (String) redisTemplate.opsForValue().get(redisKey);
        //3.修改数据库内已存数据的有效性

       /* //4.去数据库中创建该用户
        result= restTemplate.getForObject(String.format(
                "http://regist-service/user/regist/%s/%s", addr, password), ResultBean.class);*/
        restTemplate.getForObject(String.format(
                "http://regist-service/user/update-flag/%s", addr), ResultBean.class);


        return ResultBean.success("账号激活成功!!");
    }

    /**
     * 发送邮件到需要注册的邮箱上
     *
     * @param addr 需要注册的邮箱
     * @param uuid
     * @return
     */
    @RequestMapping("send/{addr}/{uuid}")
    public ResultBean sendEmail(@PathVariable String addr, @PathVariable String uuid) {
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper emailMessage = null;
        try {
            emailMessage = new MimeMessageHelper(message, true);
            emailMessage.setSubject("请激活您在本中心的账号");

            //读取模板的内容
            //设置模板要读取的数据
            Context context = new Context();
            context.setVariable("userName", addr.substring(0, addr.lastIndexOf("@")));
            context.setVariable("url", new StringBuilder().append(activeServerUrl).append(uuid).toString());
            //读取模板,并设置数据
            String emailTemplate = templateEngine.process("email", context);

            emailMessage.setText(emailTemplate, true);

            emailMessage.setFrom("xieheike@qq.com");//系统账号
            emailMessage.setTo(addr);

            sender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return ResultBean.success("email send success");
    }

}
