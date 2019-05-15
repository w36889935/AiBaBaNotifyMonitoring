package com.mytest.xmpaytest.thread;

import android.os.Handler;
import android.os.Message;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.data.User;
import com.mytest.xmpaytest.pojo.HttpRespons;
import com.mytest.xmpaytest.util.HttpRequester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆线程
 *
 * @author 王伟
 * @title LoginThread
 * @package com.mytest.xmpaytest.thread
 * @date 2019年04月13日 11:41
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class LoginThread implements Runnable {
    /**消息处理**/
    private Handler mHandler;
    /**请求对象**/
    private HttpRequester requester;
    /**请求参数**/
    private Map<String, String> Requresparam;

    /**避免魔法值**/
    private String IsSuccess = "IsSuccess";

    public LoginThread(Handler mHandler, Map Requresparam) {
        this.mHandler = mHandler;
        this.Requresparam = Requresparam;
    }

    @Override
    public void run() {
        /**初始请求对象**/
        requester = new HttpRequester();
        /**消息内容对象**/
        Map<String ,Object> messages = new HashMap<String ,Object>(2);
        try {
            /**发送请求登陆返回信息**/
            String resultContent = requester.sendGet(ConfigurationProperties.LoginUrl,Requresparam).getContent();
            /**转换为Json对象**/
            JSONObject jsonObject = JSON.parseObject(resultContent);
            /**获得消息**/
            Message message=mHandler.obtainMessage();
            /**获得返回并且判断状态**/
            if(jsonObject.get("errormsg") == null){
                /**将返回的数据转换为用户对象**/
                User user = JSON.parseObject(resultContent, User.class);
                /**设置消息状态**/
                message.what=2;
                /**设置消息内容**/
                messages.put("messags","您已成功登录");
                messages.put("user",user);
            }else {
                /**获得失败消息提示**/
                String messageBody = jsonObject.get("errormsg").toString();
                /**设置消息状态**/
                message.what=3;
                messages.put("messags",messageBody);
                messages.put("user",null);
            }
            /**设置消息内容**/
            message.obj=messages;
            /**发送消息**/
            mHandler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
