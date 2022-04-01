package com.zy.maintenance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.maintenance.bean.Manage;

public interface ManageService extends IService<Manage> {
    Boolean updatePass(String password, int manageId);

    Manage login(Manage manage);


}
