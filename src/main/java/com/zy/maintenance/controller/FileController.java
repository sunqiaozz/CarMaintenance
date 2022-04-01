package com.zy.maintenance.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.maintenance.bean.Files;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 处理文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileMapper fileMapper;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        //先存储到磁盘
        //定义一个文件的唯一标识
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID= uuid+ StrUtil.DOT+type;
        File uploadFile = new File(fileUploadPath +fileUUID);
        //判断配置的文件父级目录是否存在，若不存在则创建一个新的文件目录
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
        String url;
        //把获取到的文件存储到磁盘目录
        file.transferTo(uploadFile);
        //获取文件的MD5，通过对比文件md5避免重复上传相同内容的文件
        String md5= SecureUtil.md5(uploadFile);
        //查询文件的md5是否存在
        Files dbFiles=getFileByMD5(md5);
        //获取文件的url
        if(dbFiles!=null){
            url=dbFiles.getUrl();
            //由于文件已存在，所以删除刚才上传的文件
            uploadFile.delete();
        }else {
            //数据库若不存在重复文件，则不删除刚才上传的文件
            url="http://localhost:8090/file/"+fileUUID;
        }

        //存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);//文件大小为kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
        return url;
    }

    private Files getFileByMD5(String md5){
        QueryWrapper<Files> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("md5",md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size()==0 ? null:filesList.get(0);
    }

    /**
     * 文件下载接口
     * @param fileUUID
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
       //根据文件的唯一标识码获取文件
        File downloadFile = new File(fileUploadPath + fileUUID);
        //设置输出流的格式
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        outputStream.write(FileUtil.readBytes(downloadFile));
        outputStream.flush();
        outputStream.close();
    }
    /**
     * 搜索分页
     */
    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name
    ){
        //设置查询条件
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.eq("is_delete",false);
        queryWrapper.orderByDesc("id");
        IPage<Files> filesIPage=new Page<>(pageNum,pageSize);
        return Result.success(fileMapper.selectPage(filesIPage,queryWrapper));
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Files files){
        System.out.println(files);
        return  Result.success(fileMapper.updateById(files));
    }
    //删除全部
    @DeleteMapping("/deleteAll")
    public Result deleteAll(@RequestBody List<Integer> ids){
        QueryWrapper<Files> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for (Files file : files) {
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        return Result.success();
    }
    //删除单个
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        Files files = fileMapper.selectById(id);
        files.setIsDelete(true);
        return Result.success(fileMapper.updateById(files));
    }
}
