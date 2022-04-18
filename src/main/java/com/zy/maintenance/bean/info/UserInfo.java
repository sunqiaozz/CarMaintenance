package com.zy.maintenance.bean.info;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
//在order实体中使用--用来接收数据库三表查询的数据
public class UserInfo {
    private Integer userId;
    private String userName;
    private String telephone;
    private String email;
}
