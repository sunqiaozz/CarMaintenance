package com.zy.maintenance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.maintenance.bean.User;
import com.zy.maintenance.bean.info.UserHtmlOrder;
import com.zy.maintenance.common.StatusCode;
import com.zy.maintenance.exception.ServiceException;
import com.zy.maintenance.mapper.UserMapper;
import com.zy.maintenance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    UserMapper userMapper;

    public  Boolean saveUser(User user) {
        if (user.getUserId() == null) {
            return save(user);
        } else {
            return updateById(user);
        }
    }

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        queryWrapper.eq("password",user.getPassword());
        User userCase;
        try {
            userCase=getOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(StatusCode.CODE_500,"系统错误");
        }
        if (userCase!=null){
            return userCase;
        }else {
            throw new ServiceException(StatusCode.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public Map<String,Object> queryUserOrder(String userName, String orderStatus, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        int num=pageSize*(pageNum-1);
        List<UserHtmlOrder> orders = userMapper.queryUserOrder(userName, orderStatus, num, pageSize);
        map.put("orders",orders);
        Integer total=userMapper.queryUserOrderTotal(userName, orderStatus);
        map.put("total",total);
        return map;
    }

    @Override
    public Boolean updatePass(String password, Integer userId) {
        return userMapper.updateUserPass(password,userId);
    }
}
