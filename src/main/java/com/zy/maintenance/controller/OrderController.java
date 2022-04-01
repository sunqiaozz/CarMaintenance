package com.zy.maintenance.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.zy.maintenance.bean.Order;
import com.zy.maintenance.bean.info.OrderExport;
import com.zy.maintenance.bean.info.OrderInfo;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.service.OrderInfoService;
import com.zy.maintenance.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderInfoService orderInfoService;

    //导出文件
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        List<Order> orderList=orderService.allOrderInfo();
        ExcelWriter writer = ExcelUtil.getWriter(true);
        List<OrderExport> exportList = CollUtil.newArrayList();
        for (Order list : orderList) {
            OrderExport orderExport= new OrderExport();
            orderExport.setOrderId(list.getOrderId());
            orderExport.setUserName(list.getUserInfo().getUserName());
            orderExport.setTelephone(list.getUserInfo().getTelephone());
            orderExport.setEmail( list.getUserInfo().getEmail());
            orderExport.setPackageName( list.getPackageInfo().getPackageName());
            orderExport.setPackageContent(list.getPackageInfo().getPackageContent());
            orderExport.setPackageType(list.getPackageInfo().getPackageType());
            orderExport.setPackagePrice(Double.parseDouble(list.getPackageInfo().getPackagePrice().toString()));
            orderExport.setOrderStatus(list.getOrderStatus());
            orderExport.setOrderProgress(list.getOrderProgress());
            orderExport.setOrderTime(list.getOrderTime());
            exportList.add(orderExport);
        }
        writer.addHeaderAlias("orderId","订单编号");
        writer.addHeaderAlias("userName","用户名");
        writer.addHeaderAlias("telephone","用户联系方式");
        writer.addHeaderAlias("email","用户电子邮箱");
        writer.addHeaderAlias("packageId","套餐编号");
        writer.addHeaderAlias("packageName","套餐名称");
        writer.addHeaderAlias("packageContent","套餐内容");
        writer.addHeaderAlias("packageType","套餐类型");
        writer.addHeaderAlias("packagePrice","套餐金额");
        writer.addHeaderAlias("orderStatus","订单状态");
        writer.addHeaderAlias("orderProgress","订单进度");
        writer.addHeaderAlias("orderTime","订单时间");
        //writer.setOnlyAlias(true);
        writer.write(exportList,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("订单信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
        return Result.success();
    }

    //导入文件
    @PostMapping("/import")
    public Result importOrder(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> lists = reader.read(1);
        List<OrderInfo> orders = CollUtil.newArrayList();
        for (List<Object> list : lists) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setUser_id(Integer.parseInt(list.get(0).toString()));
            orderInfo.setPackage_id(Integer.parseInt(list.get(1).toString()));
            orderInfo.setOrder_status(list.get(2).toString());
            orderInfo.setOrder_progress(Integer.parseInt(list.get(3).toString()));
            orders.add(orderInfo);
        }
        System.out.println(orders);
        return Result.success(orderInfoService.saveBatch(orders));
    }
    //1:1:1,三表分页查询
    @GetMapping("/page")
    public Result loadPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "")String searchContent,
                           @RequestParam(defaultValue = "")String searchType){
        System.out.println(searchType+"-------------------------------"+searchContent);
        Map<String, Object> map = new HashMap<>();
        if(!"".equals(searchType)){
            switch (searchType){
                case "userName": searchType="user_name";break;
                case "packageName": searchType="package_name";break;
                case "orderStatus": searchType="order_status";break;
                case "orderProgress": searchType="order_progress";break;
                default: searchType="";break;
            }
            System.out.println(searchType);
            System.out.println(searchContent);
        }
        pageNum=(pageNum-1)*pageSize;
        List<Order> orders = orderService.orderPage(pageNum, pageSize, searchType, searchContent);
        Integer total=orderService.orderTotal(searchType, searchContent);
        map.put("total",total);
        map.put("orders",orders);
        return Result.success(map);
    }
    @DeleteMapping("/delete/{id}")
    public Result deleteOne(@PathVariable Integer id){
        return Result.success(orderService.removeById(id));
    }
    @DeleteMapping("/deleteAll")
    public Result deleteAll(@RequestBody List<Integer> ids){
        return Result.success(orderService.removeByIds(ids));
    }
    @PostMapping("/insert")
    public Result insertOrder(){

        return  Result.success();
    }

}
