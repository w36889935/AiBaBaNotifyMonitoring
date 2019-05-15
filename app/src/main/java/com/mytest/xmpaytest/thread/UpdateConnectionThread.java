package com.mytest.xmpaytest.thread;

import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.pojo.HttpRespons;
import com.mytest.xmpaytest.util.HttpRequester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 更新服务器连接
 *
 * @author 王伟
 * @title UpdateConnectionThread
 * @package com.mytest.xmpaytest.thread
 * @date 2019年05月14日 8:43
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class UpdateConnectionThread implements Runnable {
    /**
     * 请求对象
     */
    static HttpRequester requester = new HttpRequester();
    /**
     * 请求参数
     */
    static Map<String, String> params = new HashMap<>();

    static {
        requester.setDefaultContentEncoding("utf-8");
        params.put("todo","timePost");
        params.put("v",ConfigurationProperties.VERSION);
        params.put("ts","0");
        params.put("count","1");
    }

    @Override
    public void run() {
        params.put("type","101");
        params.put("account",ConfigurationProperties.ALI_ACCOUNTNO);
        params.put("userid",ConfigurationProperties.USER_ID);
        try {
            String returnResult  = requester.sendGet(ConfigurationProperties.URL,params).getContent();
            System.out.println(returnResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
