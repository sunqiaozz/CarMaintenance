package com.zy.maintenance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.maintenance.bean.News;
import com.zy.maintenance.bean.User;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.mapper.NewsMapper;
import com.zy.maintenance.service.UserService;
import com.zy.maintenance.utils.SendQQEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    UserService userService;
    @Autowired
    SendQQEmail sendQQEmail;


    //用户忘记密码
    //查询用户
    @GetMapping("/search")
    public Result searchUser(@RequestParam String userName){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        return Result.success(userService.getOne(queryWrapper));
    }
    //邮箱验证码
    @GetMapping("/code")
    public Result sendCode(@RequestParam("email")String toEmail){
        Integer emailCode = sendQQEmail.sendEmail(toEmail, new Random().nextInt(10000));
        return Result.success(emailCode);
    }
    //修改密码
    @GetMapping("/pass")
    public Result updatePass(@RequestParam("password")String password,@RequestParam("id")Integer userId){
        return  Result.success(userService.updatePass(password,userId));
    }
}
