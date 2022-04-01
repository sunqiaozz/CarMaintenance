package com.zy.maintenance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.maintenance.bean.Order;

import java.util.List;

public interface OrderService extends IService<Order> {
    List<Order> orderPage(Integer pageNum, Integer pageSize, String searchType, String searchContent);

    Integer orderTotal(String searchType, String searchContent);

    List<Order> allOrderInfo();

    Double countPrice();
}
