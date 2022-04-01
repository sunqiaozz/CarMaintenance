package com.zy.maintenance.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.zy.maintenance.bean.info.PackageNum;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.service.OrderService;
import com.zy.maintenance.service.PackageService;
import com.zy.maintenance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    PackageService packageService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @GetMapping("/example")
    public Result getValue(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y",CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }
    @GetMapping("/packageNum")
    public Result echartsNum(){
        Map<String, Object> map = new HashMap<>();
        List<String> typeList=packageService.selectAllType();
        //存放所有数据对象
        List<PackageNum> dataList = CollUtil.newArrayList();
        map.put("types",typeList);
        int q1=0;int q2=0;int q3=0;int q4=0;//第一二三四季度
        for (String type : typeList) {
            PackageNum packageNum = new PackageNum();
            List<Date> typeAllTimes=packageService.selectPackageAllTime(type);
            for (Date typeAllTime : typeAllTimes) {
                Quarter quarter = DateUtil.quarterEnum(typeAllTime);
                switch (quarter){
                    case Q1: q1 += 1; break; case Q2: q2 += 1; break;
                    case Q3: q3 += 1; break; case Q4: q4 += 1; break; default:break;
                }
            }
            packageNum.setName(type);packageNum.setNum(CollUtil.newArrayList(q1,q2,q3,q4));
            dataList.add(packageNum);
            q1=q2=q3=q4=0;
        }
        map.put("list",dataList);
        return Result.success(map);
    }
    @GetMapping("/num")
    public Result getAllNumber(){
        Map<String, Object> map = new HashMap<>();
        long userCount = userService.count();//用户总数
        long orderCount = orderService.count();//订单总数
        Double orderPriceCount=orderService.countPrice();//订单总金额
        int packageCount = packageService.selectAllType().size();//套餐总数
        map.put("userCount",userCount);map.put("orderCount",orderCount);
        map.put("orderPriceCount",orderPriceCount);map.put("packageCount",packageCount);
        return  Result.success(map);
    }
}
