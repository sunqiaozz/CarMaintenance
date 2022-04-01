package com.zy.maintenance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.maintenance.bean.User;
import com.zy.maintenance.bean.info.UserHtmlOrder;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User>  {
    Boolean saveUser(User user);

    User login(User user);

    Map<String,Object> queryUserOrder(String userName, String orderStatus, Integer pageNum, Integer pageSize);

    Boolean updatePass(String password, Integer userId);
}
