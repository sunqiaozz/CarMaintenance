package com.zy.maintenance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.maintenance.bean.Package;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    PackageService packageService;

    @GetMapping("/types")
    public Result types(){
        List<String> typeList=packageService.selectAllType();
        return Result.success(typeList);
    }
    @GetMapping ("/packages")
    public  Result packages(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String searchContent,
                            @RequestParam(defaultValue = "") String searchType){
        Map<String, Object> map = new HashMap<>();
        IPage<Package> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Package> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("package_type",searchType);
        queryWrapper.like("package_name",searchContent);
        IPage<Package> iPage = packageService.page(page, queryWrapper);
        map.put("total",iPage.getTotal());
        map.put("records",iPage.getRecords());
        return Result.success(map);
    }
}
