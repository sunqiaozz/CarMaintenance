package com.zy.maintenance.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value="user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String password;
    private String realName;
    private String gender;
    private String telephone;
    private String email;
    private String status;
    //private List<Order> orderList;
    private Double account;
    private String avatar;
}
