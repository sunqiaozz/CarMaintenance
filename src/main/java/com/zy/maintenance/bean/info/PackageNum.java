package com.zy.maintenance.bean.info;

import lombok.Data;

import java.util.List;
@Data
//用于echarts构件图表--套餐信息
public class PackageNum {
    private String name;
    private List<Integer> num;
}
