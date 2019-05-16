package com.mytest.xmpaytest.service;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.thread.PaymentNotice;
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
        /**金额**/
        String money;
        /**支付类型**/
        Boolean payType;

        //判断是否为支付通知
        if(ConfigurationProperties.ALI_PAI_PAGE.equals(sbn.getPackageName()) ||  ConfigurationProperties.WEI_PAI_PAGE.equals(sbn.getPackageName())){
            if(ConfigurationProperties.WEI_CONTENT.equals(sbn.getNotification().extras.get(ConfigurationProperties.NOTIFICATION_TITLE))){
                payType = false;
                money = regularUtil.getMoney(sbn, ".*微信支付收款(.*)元.*");
            }else if(ConfigurationProperties.ALI_CONTENT.equals(sbn.getNotification().extras.get(ConfigurationProperties.NOTIFICATION_TITLE))){
                payType = true;
                money = regularUtil.getMoney(sbn, ".*成功收款(.*)元.*");
            }else{
                return;
            }
            System.out.println(money);
            PaymentNotice paymentNotice = new PaymentNotice(payType,money);
            Thread PayNotificationThread = new Thread(paymentNotice);
            PayNotificationThread.start();
        }else{
            System.out.println("其他通知");
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        //移除通知
        System.out.println("移除通知");
    }

}
