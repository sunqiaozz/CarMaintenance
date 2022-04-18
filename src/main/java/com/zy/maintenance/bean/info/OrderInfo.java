package com.zy.maintenance.bean.info;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@TableName(value = "car_order")
//用于接收excel导入的数据
public class OrderInfo {
    @TableId(type = IdType.AUTO)
    private Integer order_id;
    private Integer user_id;
    private Integer package_id;
    private String order_status;
    private Integer order_progress;
}
