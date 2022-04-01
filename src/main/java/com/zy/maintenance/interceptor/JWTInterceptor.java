package com.zy.maintenance.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.maintenance.bean.Manage;
import com.zy.maintenance.bean.User;
import com.zy.maintenance.common.StatusCode;
import com.zy.maintenance.exception.ServiceException;
import com.zy.maintenance.service.ManageService;
import com.zy.maintenance.service.UserService;
import com.zy.maintenance.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JWTInterceptor  implements HandlerInterceptor {
    @Autowired
    private ManageService manageService;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        System.out.println(token);
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(StatusCode.CODE_401, "无token，请重新登录");
        }
        //获取token中的id
        String id;
        try {
            id= JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw new ServiceException(StatusCode.CODE_401,"token验证失败，请重新登录");
        }
        //区分id类型
        boolean contains = id.contains("Admin_");
        if (contains){
            //根据id查询用户
            QueryWrapper<Manage> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("manage_id",id.split("_")[1]);
            Manage manage = manageService.getOne(queryWrapper);
            if(manage==null){
                throw new ServiceException(StatusCode.CODE_401,"用户不存在，请重新登录");
            }
            //用户密码加签验证token
            JWTVerifier jwtVerifier = JWTUtils.requireToken(manage.getPassword());
            try {
                jwtVerifier.verify(token);
            }catch (JWTVerificationException e){
                throw new ServiceException(StatusCode.CODE_401,"token验证失败，请重新登录");
            }
        }else {
            System.out.println(id+"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",id);
            User user = userService.getOne(queryWrapper);
            System.out.println(user+"---------------------------------------");
            if(user==null){
                throw new ServiceException(StatusCode.CODE_401,"用户不存在，请重新登录");
            }
            //用户密码加签验证token
            JWTVerifier jwtVerifier = JWTUtils.requireToken(user.getPassword());
            try {
                jwtVerifier.verify(token);
            }catch (JWTVerificationException e){
                throw new ServiceException(StatusCode.CODE_401,"token验证失败，请重新登录");
            }
        }
        return true;
    }
}
