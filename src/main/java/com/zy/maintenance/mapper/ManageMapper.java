package com.zy.maintenance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.maintenance.bean.Manage;



public interface ManageMapper extends BaseMapper<Manage> {
    Boolean updatePass(String password, int manageId);

}
