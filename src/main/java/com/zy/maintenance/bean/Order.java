package com.zy.maintenance.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zy.maintenance.bean.info.PackageInfo;
import com.zy.maintenance.bean.info.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "car_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
//    private Integer userId;
//    private Integer packageId;
    private UserInfo userInfo;
    private PackageInfo packageInfo;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;
    private String orderStatus;
    private Integer orderProgress;
}
