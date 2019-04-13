package com.mytest.xmpaytest.util;

import android.service.notification.StatusBarNotification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类说明
 * 正则工具包
 *
 * @author 王伟
 * @title RegularUtil
 * @package com.mytest.mytest.util
 * @date 2019年03月04日 22:26
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class RegularUtil {
    public String getMoney(StatusBarNotification sbn, String regEx){
        String str = sbn.getNotification().extras.get("android.text").toString();
        new LogPrint().magss(sbn);
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if(mat.find()){
            return mat.group(1);
        }
        return null;
    }
}
