package com.zy.maintenance.bean.info;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
//在order实体中使用--用来接收数据库三表查询的数据
public class PackageInfo {
    private Integer packageId;
    private String packageName;
    private String packageContent;
    private String packageType;
    private Double packagePrice;
}
