package com.mytest.xmpaytest;

import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.pojo.HttpRespons;
import com.mytest.xmpaytest.pojo.PayRequest;
import com.mytest.xmpaytest.service.PaymentNotice;
import com.mytest.xmpaytest.util.HttpRequester;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println("Hello World");
        //支付宝
        PayRequest payRequest = new PayRequest("Alipay-","商品","收款码","10001","1","18273817161","101","9876392827814a3991494888910c5b13");
        HttpRespons hr  = null;
        try {
            HttpRequester requester = new HttpRequester(); //请求对象
            hr = requester.sendGet(ConfigurationProperties.URL,payRequest.getPayRequest());
            System.out.println(hr.getUrlString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}