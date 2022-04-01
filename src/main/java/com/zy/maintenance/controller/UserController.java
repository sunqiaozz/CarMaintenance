package com.zy.maintenance.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.maintenance.bean.User;
import com.zy.maintenance.bean.info.UserHtmlOrder;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.common.StatusCode;
import com.zy.maintenance.exception.ServiceException;
import com.zy.maintenance.service.UserService;
import com.zy.maintenance.utils.JWTUtils;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //查询该用户订单
    @PostMapping("/order")
    public Result userOrder(@RequestBody List<String> paramsList){
        String userName=paramsList.get(0);
        String orderStatus=paramsList.get(1);
        Integer pageNum=Integer.parseInt(paramsList.get(2));
        Integer pageSize=Integer.parseInt(paramsList.get(3));
        return Result.success(userService.queryUserOrder(userName,orderStatus,pageNum,pageSize));
    }
    //检查用户名是否存在
    @GetMapping("/rule")
    public Result ruleUserName(@RequestParam String userName){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        User user = userService.getOne(queryWrapper);
        if(user!=null){
            throw new ServiceException(StatusCode.CODE_600,"用户名已存在");
        }else {
            return  Result.success(true);
        }
    }
    //登录校验
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        String userName = user.getUserName();
        String password = user.getPassword();
        if(StrUtil.isBlank(userName) || StrUtil.isBlank(password)){
            return Result.error(StatusCode.CODE_400,"参数错误");
        }
        User loginUser=userService.login(user);
        map.put("user",loginUser);
        map.put("token", JWTUtils.getToken(loginUser.getUserId().toString(),loginUser.getPassword()));
        return Result.success(map);
    }
    //下载文件
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        //从数据库查询出所有的数据
        List<User> listUser=userService.list();
        //将用户数据写到浏览器中
        ExcelWriter writer= ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("userId","用户Id");
        writer.addHeaderAlias("userName","用户名");
        writer.addHeaderAlias("realName","姓名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("gender","性别");
        writer.addHeaderAlias("telephone","电话号码");
        writer.addHeaderAlias("email","电子邮箱");
        writer.addHeaderAlias("account","账户余额");
        // 默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除之
        writer.setOnlyAlias(true);
        //一次性写出list内的对象到excel ，默认样式
        writer.write(listUser,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out, true);
        // 关闭writer，释放内存
        out.close();
        writer.close();
        return Result.success(true);
    }
    //导入文件
    @PostMapping("/import")
    public Result importUser(MultipartFile file) throws IOException {
        InputStream inputStream=file.getInputStream();
        ExcelReader reader=ExcelUtil.getReader(inputStream);
        //1.bean对应方法，表头必须是英文对象bean属性
        List<User> list=reader.readAll(User.class);
        //2.忽略表头的写法，手动指定数据对应方式
        /*List<List<Object>> lists = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : lists) {
            User user = new User();
            user.setUserName(row.get(0).toString());
            users.add(user);
        }*/
        return Result.success(userService.saveBatch(list));
    }
    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String searchContent,
                                @RequestParam(defaultValue = "") String searchType
    ){
        Map<String, Object> map = new HashMap<>();
        IPage<User> page=new Page<>(pageNum,pageSize);
        //设置查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(searchType)){
            if(searchType.equals("userName"))searchType="user_name";
            if(searchType.equals("realName"))searchType="real_name";
            queryWrapper.like(searchType,searchContent);
        }
        queryWrapper.orderByDesc("user_id");
        IPage<User> iPage = userService.page(page, queryWrapper);
        map.put("total",iPage.getTotal());
        map.put("records",iPage.getRecords());
        return Result.success(map);
    }
    //根据用户名查询用户信息
    @GetMapping("/name/{userName}")
    public Result getUserInfo(@PathVariable String userName){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        return Result.success(userService.getOne(queryWrapper));
    }
    @PostMapping("/save")
    public Result saveUser(@RequestBody User user){
        return  Result.success(userService.saveUser(user));
    }
    @DeleteMapping("/deleteAll")
    public Result deleteAll(@RequestBody List<Integer> ids){
        return Result.success(userService.removeByIds(ids));
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(userService.removeById(id));
    }
}
