package com.zy.maintenance.bean.info;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
//用于接收数据库的订单数据--order界面显示信息时
public class UserInfo {
    private Integer userId;
    private String userName;
    private String telephone;
    private String email;
}
