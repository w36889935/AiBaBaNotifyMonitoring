package com.mytest.xmpaytest.service;



import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.pojo.HttpRespons;
import com.mytest.xmpaytest.pojo.PayRequest;
import com.mytest.xmpaytest.util.HttpRequester;
import com.mytest.xmpaytest.util.MD5Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 类说明
 * 支付通知，处理发送
 * @author 王伟
 * @title PaymentNotice
 * @package com.mytest.mytest.service
 * @date 2019年03月07日 15:00
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class PaymentNotice implements  Runnable {
    private Boolean payType;//支付类型
    private  String amount;//金额
    private HttpRequester requester = new HttpRequester(); //请求对象

    //构造函数
    public PaymentNotice(Boolean payType, String amount){
        super();
        this.payType = payType;
        this.amount = amount;
    }

    //发送通知
    public void  sendNotice()  {
        if(payType){
            //支付宝
            PayRequest payRequest = new PayRequest("Alipay-","商品","收款码",ConfigurationProperties.USER_ID,amount,ConfigurationProperties.ALI_ACCOUNTNO,"101",ConfigurationProperties.APIKEY);
            HttpRespons hr  = null;
            try {
                hr = requester.sendGet(ConfigurationProperties.URL,payRequest.getPayRequest());
                System.out.println(hr.getUrlString());
                System.out.println(hr.getPath());
                System.out.println(hr.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //微信
        }
    }

    @Override
    public void run() {
        sendNotice();
    }
}
