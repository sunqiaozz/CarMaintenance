package com.zy.maintenance.bean.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserHtmlOrder {
    //用于接收用户界面的订单数据
    private Integer userId;
    private Integer orderId;
    private String userName;
    private String orderStatus;
    private Integer orderProgress;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;
    private String packageName;
    private String packageType;
    private Double packagePrice;
}
