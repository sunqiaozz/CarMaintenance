package com.zy.maintenance.listener;

import com.zy.maintenance.service.OrderService;
import com.zy.maintenance.vo.PayAsyncVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class PayListener {
    @Autowired
    OrderService orderService;

    @PostMapping("/payed/notify")
    public String handleAlipayed(HttpServletRequest request,PayAsyncVo asyncVo){
        //只要我们收到了支付宝传来的异步请求的通知，告诉我们订单支付成功，返回success以后，支付宝就不再通知了
//        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(asyncVo+"*************************");
        orderService.updateStatus(Integer.parseInt(asyncVo.getOut_trade_no()));
        return "success";
    }
}
