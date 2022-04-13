package com.zy.maintenance.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zy.maintenance.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String app_id="2021000119664367";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC/El2cybanAyVAGYmvtN1ETyvosZNmx/6k8/2J9oy4nI3PUQf4ET/7XGldNVGkYBffevEEHz7g/OkTABOk6rE/DU4tFI9Cz9ijVmZ3dezFEGs08+4dpgd8HdgGOxEoqP06dGdSmgCdKGrZkMi/+Mrpd0AC0iayt1HczZ1sjMf5RDa7qkek3Kx1N1qGvSO1txCAw9a/zSvtQEOm3eWYQYW4sNNRRWMVV0ETYmLJvgpvp0A4DY39hxJQw/1+BiMJfVybPYdAohmVPzdMqoXgoSFyOGiuwMzCZpJCuD2Qu1dBAc/j6X/vbgWUC9lQ240UToTldDpNjdqIUlE3msy7lN+LAgMBAAECggEAR/3ugaXLCVB5F5bHtwlpmVRPUgSeakqOmq5H6FSaHvD1yZhZbVFqjxX5qSHu2gVO2WhkKU5wjasdUohj3m6DTCOCQiAN/nl7wcdLIlWvWWzfR/tky40EIvLHs0lMJMrPZtf3GjtmYdCZudIAobxnD+JbnvR5mjrSQGTEnOLKchNnsqTPRn9uZ8Pp+8Kqs795LzBsVBnEsMWgKtZt9jfXBSuZUjtGfrpuplQwy6z9QP2f5cvrKjrXlsN8SBkUa3PHfPTRX9/sMJ2gTPuEf2D4IIU8d/Q+qUZQN7sUV2R3Jwu/jzu5QhkBHTCtCtf19I35Q06yQbqIG4NwWtIfuLIX4QKBgQDp8bfGeX18+bCtXmCiYUAVRo2TktOFvqqnSbMKtlVgVyZW4fqQ4U6D0Ie7K05mUhPilI+1xaq4xMZt6BQTfzYImJlb2xvIhSlwdeExTQ9DHLtbNMSfVLbCk5KJGQHeqqzxp4pKpCCP2LtbHzUYhnDHzPx3vwOWDnkd/2Bqr5yLOwKBgQDRFenmia0I3LyurLbMt3srosfdrJ3T+qlhw1oPOGiaZZFC3efBAPx7MVhrWEBrMFd8P7qWSnr4tbp+iStVsLKkDHdhGjlMF2AMiuD+dM4eOPwn7pGFxfb0/uO6BQhu5hvnYUbPDbfQ4QsSLpIzHEBlV//F8+9B1PNg/73y6+KX8QKBgE1ngNrWj8KuCtsSAv83FjDz6xwM1JfIAkbfh7Hfqlf5/eF5W6JcKdJLSN2Q8pvARHwPk40dHeOMl+r9gscArCwj8XoHASZTiOaA2kWprYcTFpqS1wuwx9eRZEagU5IM/rPmL0eLDosJttPre3Hl6aLyHAay2MjpeQGH4arq9+zJAoGAdAX0AKDfay6KaWegHKOobsNqo0EZZ7sN6RktTYrgyOSZogBfjUNN19K/HPog7RorQeBfWuzDpmW5smPNr0RZLR1Z1YFm0Cmce/lI9wq8Ihs/Ga2SBSigbL1QgzYtwtRgIZpiX51eJkPrD9DWAhSoS1R0s1U0rFROJcJklnt9BxECgYEA1gFij6rbNiAfAqBG1yOiIBnCvpT4ClIFI9KmNrZZPIzXtjuUUzuaHs+C99kJgKQLjmjUQnh73J2j8FVU9vbmWbAsXXnHjVaicaScNmsy5nlRmk8TJZ/8qPRSgV+REhlTqLoL1TWtFGdNR26COVmv8G/dYnXb+4otmIz5qNdC8Jk=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv6P2GIvsFjIJwiio91zPhulq8KtmMvBIJgxOCYXxqpIBLZl1ZwEW43/diaONX7dHqF7J1VvqJ6dAooFd++fnrax0aa21aXDh4mYj+38OjDn5zFnKeoOfUIMH5VY3O7PU+/c7+whjv3188daZ4AXobMNVOc1lDp1+Ni7jKiYGOp4Q5f6xY5VRx12XjHCeWXgZKLGaN+CvrwN0J0xia7mY31BzGM6GtobqvVUP1pLJ6jgK0SX6S98Oq3WhfaZGcroTF+XfRVEY/BugnjIhcTaYvNVV9ccPq7xk+5IviExNwjT5963AY4Tz4txo8shpJ8mLYtsPKfUWUmrzZfWtdiqcDwIDAQAB";

    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    public String notify_url = "http://5h17185z52.zicp.vip/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    public String return_url = "http://localhost:8080/user/order";

    // 签名方式
    private  String sign_type= "RSA2";

    // 字符编码格式
    private  String charset= "utf-8";

    //订单超时时间
    private String timeout = "1m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    public String gatewayUrl= "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;
    }

}
