package com.mytest.xmpaytest.util;

import android.service.notification.StatusBarNotification;

/**
 * 类说明
 *调试打印
 * @author 王伟
 * @title LogPrint
 * @package com.mytest.mytest.util
 * @date 2019年03月04日 22:27
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class LogPrint {
    public void magss(StatusBarNotification sbn){
        System.out.println("包名：" + "-----" +  sbn.getPackageName());
        System.out.println("可访问性服务通知的文本：" + "------" + sbn.getNotification().tickerText);
        System.out.println("头部：" + "-----" + sbn.getNotification().extras.get("android.title"));
        System.out.println("文本内容：" + "-----" + sbn.getNotification().extras.get("android.text"));
    }
}
