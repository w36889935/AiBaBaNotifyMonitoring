package com.mytest.xmpaytest.service;

import android.os.Handler;
import android.os.Message;
import com.mytest.xmpaytest.data.User;
import com.mytest.xmpaytest.pojo.HttpRespons;
import com.mytest.xmpaytest.util.CheckTools;
import com.mytest.xmpaytest.util.HttpRequester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;

/**
 * 用户登陆服务
 *
 * @author 王伟
 * @title LoginServer
 * @package com.mytest.xmpaytest.service
 * @date 2019年03月15日 11:04
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class LoginServer {
    /**消息处理**/
    private Handler mHandler;
    /**请求对象**/
    private HttpRequester requester;
    /**校验工具**/
    private CheckTools checkTools = new CheckTools();
    private Map<String, String> map = new HashMap<String, String>();

    /**
     * 构造函数
     * @param mHandler 消息处理对象
     */
    public LoginServer(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public String Login(String userName, String passWord){
        if(checkTools.isNullValue(userName)){
            return  "请输入正确的账号";
        }
        if(checkTools.isNullValue(passWord)){
            return "请输入正确的密码";
        }
        map.put("username",userName);
        map.put("password",passWord);
        map.put("IdentifyingCode","Android");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    requester = new HttpRequester(); //请求对象
                    Map<String ,Object> messages = new HashMap<String ,Object>();
                    HttpRespons httpRespons = requester.sendGet("http://www.crjcc.com/Home/LoginPost",map);
                    Message message=mHandler.obtainMessage();
                    if(httpRespons.getContent().indexOf("IsSuccess")>0){
                        message.what=2;
                        User newPerson = JSON.parseObject(httpRespons.getContent().toString(), User.class);
                        messages.put("messags","您已成功登录");
                        messages.put("user",newPerson);
                    }else {
                        message.what=3;
                        messages.put("messags",httpRespons.getContent().toString().replace("{\"errormsg\":\"","").replace("\"}",""));
                        messages.put("user",null);
                    }
                    message.obj=messages;
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return "";

    }
}
