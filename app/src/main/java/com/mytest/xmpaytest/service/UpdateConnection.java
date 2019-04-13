package com.mytest.xmpaytest.service;

import android.content.Context;
import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.pojo.HttpRespons;
import com.mytest.xmpaytest.util.HttpRequester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类说明
 *
 * @author 王伟
 * @title UpdateConnection
 * @package com.mytest.mytest.util
 * @date 2019年03月06日 22:05
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class UpdateConnection implements Runnable  {
    static ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10);//线程池
    public Context applicationContext;//上下文
    static HttpRequester requester = new HttpRequester(); //请求对象
    static Map<String, String> params = new HashMap<>(); //请求参数

    static {
        requester.setDefaultContentEncoding("utf-8");
        params.put("todo","timePost");
        params.put("v",ConfigurationProperties.VERSION);
        params.put("ts","0");
        params.put("count","1");
    }

    @Override
    public void run() {
        post();
    }

    public String post(){
        params.put("type","101");
        params.put("account",ConfigurationProperties.ALI_ACCOUNTNO);
        params.put("userid",ConfigurationProperties.USER_ID);
        try {
            HttpRespons hr  = requester.sendGet(ConfigurationProperties.URL,params);
            System.out.println(hr.getContent());
            return hr.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void init(){
        service.scheduleAtFixedRate(
                new UpdateConnection(), 1,
                2, TimeUnit.SECONDS);
    }
}
