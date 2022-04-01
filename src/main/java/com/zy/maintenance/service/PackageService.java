package com.zy.maintenance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.maintenance.bean.Package;
import com.zy.maintenance.bean.User;

import java.util.Date;
import java.util.List;

public interface PackageService extends IService<Package> {
    Boolean savePackage(Package package1);

    List<String> selectAllType();

    List<Date> selectPackageAllTime(String type);
}
