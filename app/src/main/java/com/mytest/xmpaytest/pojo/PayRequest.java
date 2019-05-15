package com.mytest.xmpaytest.pojo;

import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.util.MD5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 类说明
 * 支付请求实体
 * @author 王伟
 * @title PayRequest
 * @package com.mytest.xmpaytest.pojo
 * @date 2019年03月11日 9:19
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class PayRequest {
    private String tradeNo; //订单号
    private String desc;
    private String time;//时间
    private String username;//用户名
    private String userid;//用户ID
    private String amount;//金额
    private String status;//状态
    private String account;//账号
    private String todo;
    private String ts;
    private String ua;
    private String type;//支付类型
    private String v;//版本号
    private String sig;//密匙

    public PayRequest(String tradeNo, String desc, String username, String userid, String amount, String account, String type,String apiKey) {
        MD5Util md5Util = new MD5Util();//加密工具
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            this.tradeNo = URLEncoder.encode(tradeNo+UUID.randomUUID().toString().replaceAll("-", ""),"utf-8"); //订单号生成, "utf-8");
            this.desc = URLEncoder.encode(desc, "utf-8");
            this.time =  URLEncoder.encode(df.format(new Date()), "utf-8");
            this.username = URLEncoder.encode(username, "utf-8");
            this.userid = URLEncoder.encode(userid, "utf-8");
            this.amount = URLEncoder.encode(amount, "utf-8");
            this.status = URLEncoder.encode("成功", "utf-8");
            this.account = URLEncoder.encode(account, "utf-8");
            this.todo = "alipay";
            this.ts = "25";
            this.ua = URLEncoder.encode("Mozilla%2f5.0%2520(Windows%2520NT%252010.0%3b%2520Win64%3b%2520x64)%2520AppleWebKit%2f537.36%2520(KHTML%2c%2520like%2520Gecko)%2520Chrome%2f61.0.3163.100%2520Safari%2f537.36  Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36","utf-8");
            this.type = URLEncoder.encode(type, "utf-8");
            this.v = URLEncoder.encode(ConfigurationProperties.VERSION, "utf-8");
            this.sig = md5Util.MD5(this.tradeNo + userid + this.amount + apiKey); //密匙
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getPayRequest(){
        Map<String, String> requestParameters = new HashMap<String, String>();
        requestParameters.put("tradeNo",tradeNo);//订单号
        requestParameters.put("desc",desc);//
        requestParameters.put("time",time);//订单时间
        requestParameters.put("username",username);//
        requestParameters.put("userid",userid);//
        requestParameters.put("amount",amount);//
        requestParameters.put("status",status);//
        requestParameters.put("account",account);//账号
        requestParameters.put("todo",todo);//
        requestParameters.put("ts",ts);//
        requestParameters.put("ua",ua);//
        requestParameters.put("type",type);//
        requestParameters.put("v",v);//
        requestParameters.put("sig",sig);//密匙
        return requestParameters;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public String getDesc() {
        return desc;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public String getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getAccount() {
        return account;
    }

    public String getTodo() {
        return todo;
    }

    public String getTs() {
        return ts;
    }

    public String getUa() {
        return ua;
    }

    public String getType() {
        return type;
    }

    public String getV() {
        return v;
    }

    public String getSig() {
        return sig;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setV(String v) {
        this.v = v;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }
}
