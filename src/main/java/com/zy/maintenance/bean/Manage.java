package com.zy.maintenance.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Manage {
    private Integer manageId;
    private String manageName;
    private String password;
    private String email;
    private String telephone;
    private String avatar;
}

