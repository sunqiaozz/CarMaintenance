package com.zy.maintenance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.maintenance.bean.Package;
import com.zy.maintenance.mapper.PackageMapper;
import com.zy.maintenance.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PackageServiceImpl extends ServiceImpl<PackageMapper, Package> implements PackageService {
    @Autowired
    PackageMapper packageMapper;

    @Override
    public Boolean savePackage(Package package1) {
        if (package1.getPackageId() == null) {
            return save(package1);
        } else {
            return updateById(package1);
        }
    }

    @Override
    public List<String> selectAllType() {
        return packageMapper.selectAllType();
    }

    @Override
    public List<Date> selectPackageAllTime(String type) {
        return packageMapper.selectPackageAllTime(type);
    }
}
