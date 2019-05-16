package com.mytest.xmpaytest.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * 配置参数
 *
 * @author 王伟
 * @title ConfigurationProperties
 * @package com.mytest.mytest.config
 * @date 2019年03月07日 15:02
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class ConfigurationProperties {
    /**请求服务器URl**/
    public static String URL = "http://www.crjcc.com/Gateway";
    /**登陆服务器URL**/
    public static String LoginUrl = "http://www.crjcc.com/Home/LoginPost";
    /**版本号**/
    public static String VERSION = "2017-03-05 08:04:02";
    /**初始化表**/
    public static List<String> INIT_TABLE;
    /**用户ID**/
    public static String USER_ID;
    /**密匙**/
    public static String APIKEY;
    /**支付宝收款账户**/
    public static String ALI_ACCOUNTNO;

    static {
        /***初始化表*/
        INIT_TABLE = new ArrayList<String>();
        /**用户信息表**/
        INIT_TABLE.add("create table Users(ID integer primary key autoincrement,userID varchar(64),userName varchar(64),apiKey varchar(64),userBalance varchar(64),isSuccess varchar(64))");
        /**支付方式配置表**/
        INIT_TABLE.add("create table Accounts(ID integer primary key autoincrement,accountType varchar(64),accountNO varchar(64),AccountStatus varchar(64))");
        /**本地订单处理表**/
        INIT_TABLE.add("create table Orders(ID integer primary key autoincrement,OrderID varchar(64),OrderTime varchar(64),Status varchar(64),CallbackResult varchar(64))");
    }

    /**支付宝包名**/
    public static String ALI_PAI_PAGE= "com.eg.android.AlipayGphone";
    /**微信包名**/
    public static String WEI_PAI_PAGE= "com.eg.android.AlipayGphone";
    /**通知标题**/
    public static String NOTIFICATION_TITLE= "android.title";
    /**支付宝标题内容**/
    public static String ALI_CONTENT= "支付宝通知";
    /**微信标题内容**/
    public static String WEI_CONTENT= "微信支付";

}
