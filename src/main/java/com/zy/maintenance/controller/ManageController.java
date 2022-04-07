package com.zy.maintenance.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.maintenance.bean.Manage;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.common.StatusCode;
import com.zy.maintenance.service.ManageService;
import com.zy.maintenance.utils.JWTUtils;
import com.zy.maintenance.utils.SendQQEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/manage")
public class ManageController {
    private static String MANAGE_CODE="Admin_";
    @Autowired
    ManageService manageService;
    @Autowired
    SendQQEmail sendQQEmail;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Manage manage){
        Map<String, Object> map = new HashMap<>();
        String manageName = manage.getManageName();
        String password = manage.getPassword();
        System.out.println(manage);
        if(StrUtil.isBlank(manageName) || StrUtil.isBlank(password)){
            return Result.error(StatusCode.CODE_400,"参数错误");
        }
        Manage loginManager = manageService.login(manage);
        map.put("manager",loginManager);
        map.put("token", JWTUtils.getToken(MANAGE_CODE+loginManager.getManageId().toString(),loginManager.getPassword()));
        return  Result.success(map);
    }
    //根据用户名查询用户信息
    @GetMapping("/name/{manageName}")
    public Result findManageByName(@PathVariable String manageName){
        QueryWrapper<Manage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("manage_name",manageName);
        return Result.success(manageService.getOne(queryWrapper));
    }
    //保存修改信息
    @PostMapping("/save")
    public Result updateInfo(@RequestBody Manage manage){
        QueryWrapper<Manage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("manage_id",manage.getManageId());
        return Result.success(manageService.update(manage,queryWrapper));
    }
    //修改密码
    @GetMapping ("/pass")
    public Result updatePass(@RequestParam("password") String password,@RequestParam("id") int manageId){
        return Result.success(manageService.updatePass(password,manageId));
    }
    //邮箱验证码
    @GetMapping ("/code")
    public Result sendCode(@RequestParam("email") String toEmail){
        Integer emailCode = sendQQEmail.sendEmail(toEmail,new Random().nextInt(10000));
        return Result.success(emailCode);
    }
    //查询用户
    @GetMapping("/search")
    public Result searchManage(@RequestParam("username") String name){
        QueryWrapper<Manage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("manage_name",name);
        Manage manage = manageService.getOne(queryWrapper);
        return  Result.success(manage);
    }
}
