package com.zy.maintenance.bean.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
//用于excel导出用户信息
public class OrderExport {
    private Integer orderId;
    //private Integer userId;
    private String userName;
    private String telephone;
    private String email;
    //private Integer packageId;
    private String packageName;
    private String packageContent;
    private String packageType;
    private Double packagePrice;
    private String orderStatus;
    private Integer orderProgress;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;
}
