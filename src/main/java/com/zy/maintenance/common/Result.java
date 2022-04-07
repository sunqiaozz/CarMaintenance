package com.zy.maintenance.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Result {
    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        return  new Result(StatusCode.CODE_200,"成功",null);
    }
    public static Result success(Object data){
        return  new Result(StatusCode.CODE_200,"成功",data);
    }
    public static Result error(String code,String msg){
        return  new Result(code,msg,null);
    }
    public static Result error(){
        return  new Result(StatusCode.CODE_500,"系统错误",null);
    }
}
