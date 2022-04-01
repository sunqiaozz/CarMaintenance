package com.zy.maintenance.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.zy.maintenance.bean.Manage;
import com.zy.maintenance.service.ManageService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {
    private static  final  String SIGN="++!#%*&*(@Zhang2022@++";

    private static ManageService staticManageService;
    @Resource
    private ManageService manageService;
    @PostConstruct
    public void setManageService(){
        staticManageService=manageService;
    }

    /**
     * 生成token
     * string 传值
     */
    public static String getToken(String id,String password){
        JWTCreator.Builder builder = JWT.create();
        //创建payload
        String token =builder.withAudience(id)
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(password));
        return token;
    }
    //验证token
    public static JWTVerifier requireToken(String password){
        return JWT.require(Algorithm.HMAC256(password)).build();
    }
    /**
     * 获取当前登录用户的信息
     */
    public static Manage getCurrentUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)){
                String id = JWT.decode(token).getAudience().get(0);
                return staticManageService.getById(Integer.valueOf(id));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    //map传值
//    public static String getToken(Map<String,String> map){
//        JWTCreator.Builder builder = JWT.create();
//        //创建payload
//        map.forEach((k,v)->{
//            builder.withClaim(k,v);
//        });
//        String token = builder.withExpiresAt(DateUtil.offsetHour(new Date(),2)).sign(Algorithm.HMAC256(SIGN));
//        return token;
//    }

}
