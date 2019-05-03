package com.mytest.xmpaytest.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import com.mytest.xmpaytest.thread.LoginThread;
import com.mytest.xmpaytest.util.CheckTools;
import com.mytest.xmpaytest.util.HttpRequester;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

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
    /**初始化消息内容**/
    private String message;
    /**上下文**/
    private Context context;
    /**校验工具**/
    private CheckTools checkTools = new CheckTools();
    private Map<String, String> map = new HashMap<String, String>();

    /**
     * 构造函数
     * @param mHandler 消息处理对象
     */
    public LoginServer(Handler mHandler,Context context) {
        this.mHandler = mHandler;
        this.context = context;
    }

    /**
     * 方法说明 登陆
     * @param  userName 用户名, passWord 用户密码, context 上下文
     * @return void
     * @throws
     */
    public void Login(String userName, String passWord){
        /***判断是否开启通知权限*/
        if(isEnabled()){
            if(checkTools.isNullValue(userName)){
                message = "请输入正确的账号";
            }
            if(checkTools.isNullValue(passWord)){
                message = "请输入正确的密码";
            }
            map.put("username",userName);
            map.put("password",passWord);
            map.put("IdentifyingCode","Android");
            /**创建线程池**/
            ExecutorService cachedThreadPoolLogin = Executors.newCachedThreadPool();
            /**添加线程任务**/
            cachedThreadPoolLogin.execute(new LoginThread(mHandler , map));
            /**显示提示**/
            if(message != null){
                new QMUITipDialog.Builder(context)
                        .setIconType(3)
                        .setTipWord(message)
                        .create(true)
                        .show();
            }
        }else{
            getNotification();
            Toast.makeText(context,"请授权通知权限！",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 方法说明 判断是否打开了通知监听权限
     * @param
     * @return
     * @throws
     */
    private boolean isEnabled() {
        String pkgName = context.getPackageName();
        final String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 方法说明 获取监听权限
     * @param
     * @return
     * @throws
     */
    private void getNotification(){
        try {
            Intent intent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            } else {
                intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
