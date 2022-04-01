package com.zy.maintenance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.maintenance.bean.Package;

import java.util.Date;
import java.util.List;


public interface PackageMapper extends BaseMapper<Package> {
    List<String> selectAllType();
    List<Date> selectPackageAllTime(String type);
}
