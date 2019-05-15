package com.mytest.xmpaytest.service;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import com.mytest.xmpaytest.util.RegularUtil;

/**
 * 通知栏消息获取
 *
 * @author 王伟
 * @title notificationcollectorservice
 * @package com.mytest.mytest
 * @date 2019年03月04日 19:54
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class PayNotificationCollectorService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        RegularUtil regularUtil = new RegularUtil();
        String money = "";
        if(
                "com.eg.android.AlipayGphone".equals(sbn.getPackageName()) &&
                "支付宝通知".equals(sbn.getNotification().extras.get("android.title"))
                ){
            money = regularUtil.getMoney(sbn, ".*成功收款(.*)元.*");
            System.out.println(money);
            if(money != null){
                PaymentNotice paymentNotice = new PaymentNotice(true,money);
                Thread thread2 = new Thread(paymentNotice);
                thread2.start();
            }
        }else if(
                sbn.getPackageName().equals("com.tencent.mm") &&
                sbn.getNotification().extras.get("android.title").equals("微信支付")
                ){
            money = regularUtil.getMoney(sbn, ".*微信支付收款(.*)元.*");
            System.out.println(money);
            PaymentNotice paymentNotice = new PaymentNotice(false,money);
            Thread thread2 = new Thread(paymentNotice);
            thread2.start();
        }else {
            System.out.println("其他通知");
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        //移除通知
    }

}
