package com.zy.maintenance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.maintenance.bean.Manage;
import com.zy.maintenance.common.StatusCode;
import com.zy.maintenance.exception.ServiceException;
import com.zy.maintenance.mapper.ManageMapper;
import com.zy.maintenance.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl extends ServiceImpl<ManageMapper, Manage> implements ManageService {
    @Autowired
    ManageMapper manageMapper;
    @Override
    public Boolean updatePass(String password, int manageId) {
        return manageMapper.updatePass(password,manageId);
    }

    @Override
    public Manage login(Manage manage) {
        QueryWrapper<Manage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("manage_name",manage.getManageName());
        queryWrapper.eq("password",manage.getPassword());
        Manage one;
        try {
            one = getOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(StatusCode.CODE_500,"系统错误");
        }
        if(one!=null){
            //BeanUtil.copyProperties();
            return one;
        }else {
            throw new ServiceException(StatusCode.CODE_600,"用户名或密码错误");
        }
    }
}
