package com.zy.maintenance.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.maintenance.bean.Package;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/package")
public class PackageController {
    @Autowired
    PackageService packageService;

    //导出文件
    @GetMapping("/export")
    public Result ecportPackage(HttpServletResponse response) throws IOException {
        List<Package> listPackage = packageService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("packageId","套餐ID");
        writer.addHeaderAlias("packageName","套餐名称");
        writer.addHeaderAlias("packageContent","套餐内容");
        writer.addHeaderAlias("packageType","套餐类型");
        writer.addHeaderAlias("packagePrice","套餐价格");
        //写出
        writer.write(listPackage,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("套餐信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
        return Result.success(true);
    }
    //导入文件
    @PostMapping("import")
    public  Result importPackage(MultipartFile file) throws IOException {
        InputStream inputStream=file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> lists = reader.read(1);
        List<Package> packages = CollUtil.newArrayList();
        for (List<Object> list : lists) {
            Package aPackage = new Package();
            aPackage.setPackageName(list.get(0).toString());
            aPackage.setPackageContent(list.get(1).toString());
            aPackage.setPackageType(list.get(2).toString());
            aPackage.setPackagePrice(Double.parseDouble(list.get(3).toString()));
            packages.add(aPackage);
        }
        return  Result.success(packageService.saveBatch(packages));
    }
    //分页遍历数据
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String searchContent,
                                   @RequestParam(defaultValue = "") String searchType
    ){
        Map<String, Object> map = new HashMap<>();
        IPage<Package> page=new Page<>(pageNum,pageSize);
        //设置查询条件
        QueryWrapper<Package> queryWrapper = new QueryWrapper<>();
        if(!"".equals(searchType)){
            if(searchType.equals("packageName"))searchType="package_name";
            if(searchType.equals("packageContent"))searchType="package_content";
            if(searchType.equals("packageType"))searchType="package_type";
            queryWrapper.like(searchType,searchContent);
        }
        queryWrapper.orderByDesc("package_id");
        IPage<Package> iPage = packageService.page(page, queryWrapper);
        map.put("total", iPage.getTotal());
        map.put("records",iPage.getRecords());
        return Result.success(map);
    }
    @PostMapping("/save")
    public Result savePackage(@RequestBody Package package1){
        return  Result.success(packageService.savePackage(package1));
    }
    @DeleteMapping("/deleteAll")
    public Result deleteAll(@RequestBody List<Integer> ids){
        return Result.success(packageService.removeByIds(ids));
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){ return Result.success(packageService.removeById(id)); }
}
