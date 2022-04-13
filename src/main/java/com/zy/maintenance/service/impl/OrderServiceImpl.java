package com.zy.maintenance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.maintenance.bean.Order;
import com.zy.maintenance.mapper.OrderMapper;
import com.zy.maintenance.service.OrderService;
import com.zy.maintenance.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> orderPage(Integer pageNum, Integer pageSize, String searchType, String searchContent) {
        return orderMapper.orderPage(pageNum,pageSize,searchType,searchContent);
    }

    @Override
    public Integer orderTotal(String searchType, String searchContent) {
        return orderMapper.orderTotal(searchType,searchContent);
    }

    @Override
    public List<Order> allOrderInfo() {
        return orderMapper.allOrderInfo();
    }

    @Override
    public Double countPrice() {
        return orderMapper.countPrice();
    }

    @Override
    public Boolean saveOrderVo(OrderVo orderVo) {
        Boolean saveStatus=orderMapper.saveOrderVo(orderVo);
        return saveStatus;
    }

    @Override
    public void updateStatus(int orderId) {
        orderMapper.updateStatus(orderId);
    }
}
