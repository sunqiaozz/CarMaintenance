package com.zy.maintenance.bean.info;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
//用于接收数据库的订单数据--order界面显示信息时
public class PackageInfo {
    private Integer packageId;
    private String packageName;
    private String packageContent;
    private String packageType;
    private Double packagePrice;
}
