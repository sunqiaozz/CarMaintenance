package com.zy.maintenance;


import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.Date;
import java.util.Random;

@SpringBootTest
class MaintenanceApplicationTests {
//    @Autowired
//    JavaMailSender javaMailSender;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("996562383@qq.com");
//        message.setTo("1757810778@qq.com");
//        message.setSubject("");
//        message.setText("http");
//        javaMailSender.send(message);
//        Random random = new Random(1);
//        System.out.println(random.nextInt(10000));
//        Date date = DateUtil.date();
//        System.out.println(date);
//        System.out.println(DateUtil.quarter(date));
//        System.out.println(DateUtil.quarterEnum(date));
        redisTemplate.opsForValue().set("zz","qq");
        System.out.println(redisTemplate.keys("*"));
        System.out.println("---------------------");
        System.out.println(redisTemplate.opsForValue().get("zz").toString());
    }

}
