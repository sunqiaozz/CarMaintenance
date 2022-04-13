package com.zy.maintenance.controller;

import com.alipay.api.AlipayApiException;
import com.zy.maintenance.common.Result;
import com.zy.maintenance.config.AlipayTemplate;
import com.zy.maintenance.service.OrderService;
import com.zy.maintenance.vo.PayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alipay")
public class PayController {
    @Autowired
    AlipayTemplate alipayTemplate;
    @Autowired
    OrderService orderService;

    @ResponseBody
    @GetMapping(value = "/payOrder",produces = "text/html")
    public String payOrder(@RequestParam String orderId,
                           @RequestParam String packageName,
                           @RequestParam String packagePrice) throws AlipayApiException {
        PayVo payVo = new PayVo();
        payVo.setOut_trade_no(orderId);//订单号
        payVo.setSubject(packageName+"套餐");
        payVo.setTotal_amount(packagePrice);
        payVo.setBody("套餐支付:"+packageName);
        System.out.println(payVo);
        String pay = alipayTemplate.pay(payVo);
        orderService.updateStatus(Integer.parseInt(orderId));
        return pay;
    }
}
