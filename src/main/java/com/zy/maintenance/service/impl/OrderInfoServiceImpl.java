package com.zy.maintenance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.maintenance.bean.info.OrderInfo;
import com.zy.maintenance.mapper.OrderInfoMapper;
import com.zy.maintenance.service.OrderInfoService;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
}
