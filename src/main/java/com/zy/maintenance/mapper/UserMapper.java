package com.zy.maintenance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.maintenance.bean.User;
import com.zy.maintenance.bean.info.UserHtmlOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    List<UserHtmlOrder> queryUserOrder(@Param("userName") String userName, @Param("orderStatus") String orderStatus,
                                       @Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

    Integer queryUserOrderTotal(String userName, String orderStatus);

    Boolean updateUserPass(String password, Integer userId);
}
