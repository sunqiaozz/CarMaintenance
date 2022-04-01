package com.zy.maintenance.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class  SendQQEmail {
    @Autowired
    JavaMailSender javaMailSender;
    public Integer sendEmail(String toEmail,int code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("996562383@qq.com");
        message.setTo(toEmail);
        message.setSubject("汽车管理系统后台重置密码");
        message.setText("您的验证码为:"+code+",请勿泄露于他人！");
        javaMailSender.send(message);
        return code;
    }
}
