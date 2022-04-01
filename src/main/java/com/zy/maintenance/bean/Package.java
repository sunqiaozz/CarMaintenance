package com.zy.maintenance.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "package")
public class Package {
    @TableId(type = IdType.AUTO)
    private Integer packageId;
    private String packageName;
    private String packageContent;
    private String packageType;
    private Double packagePrice;
    private String packageAvatar;
}