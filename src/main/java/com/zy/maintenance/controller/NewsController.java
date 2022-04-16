package com.zy.maintenance.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.maintenance.bean.News;
import com.zy.maintenance.bean.Package;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.mapper.NewsMapper;
import com.zy.maintenance.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsMapper newsMapper;

    @Autowired
    NewsService newsService;

    //养护百科模块
    @GetMapping("/query")
    public Result getAllNews(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("new_id");
        List<News> newsList = newsMapper.selectList(queryWrapper);
        Long total = newsMapper.selectCount(queryWrapper);
        map.put("records",newsList);
        map.put("total",total);
        return Result.success(map);
    }
    //导出文件
    @GetMapping("/export")
    public Result ecportNews(HttpServletResponse response) throws IOException {
        List<News> newsList = newsService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("newId","百科ID");
        writer.addHeaderAlias("newName","百科名称");
        writer.addHeaderAlias("newContent","百科内容");
        writer.addHeaderAlias("newTime","发布时间");
        //写出
        writer.write(newsList,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("百科信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
        return Result.success(true);
    }
    //导入文件
    @PostMapping("import")
    public  Result importNews(MultipartFile file) throws IOException {
        InputStream inputStream=file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> lists = reader.read(1);
        List<News> newsList = CollUtil.newArrayList();
        for (List<Object> list : lists) {
            News news = new News();
            news.setNewName(list.get(0).toString());
            news.setNewContent(list.get(1).toString());
            newsList.add(news);
        }
        return  Result.success(newsService.saveBatch(newsList));
    }
    //分页遍历数据
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String searchContent
    ){
        Map<String, Object> map = new HashMap<>();
        IPage<News> page=new Page<>(pageNum,pageSize);
        //设置查询条件
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        if(!"".equals(searchContent)){
            queryWrapper.like("new_name",searchContent);
        }
        queryWrapper.orderByDesc("new_id");
        IPage<News> iPage = newsService.page(page, queryWrapper);
        map.put("total", iPage.getTotal());
        map.put("records",iPage.getRecords());
        return Result.success(map);
    }
    @PostMapping("/save")
    public Result saveNews(@RequestBody News news){ return  Result.success(newsService.saveNews(news)); }
    @DeleteMapping("/deleteAll")
    public Result deleteAll(@RequestBody List<Integer> ids){
        return Result.success(newsService.removeByIds(ids));
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){ return Result.success(newsService.removeById(id)); }
}
