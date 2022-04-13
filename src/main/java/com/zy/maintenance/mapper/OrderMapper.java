package com.zy.maintenance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.maintenance.bean.Order;
import com.zy.maintenance.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    List<Order> orderPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("searchType") String searchType, @Param("searchContent") String searchContent);

    Integer orderTotal(@Param("searchType") String searchType, @Param("searchContent") String searchContent);

    List<Order> allOrderInfo();

    Double countPrice();

    Boolean saveOrderVo(OrderVo orderVo);

    void updateStatus(int orderId);
}