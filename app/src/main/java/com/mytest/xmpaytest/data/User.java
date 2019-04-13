package com.mytest.xmpaytest.data;

import java.util.List;
import java.util.Map;

/**
 * 类说明
 *
 * @author 王伟
 * @title User
 * @package com.mytest.xmpaytest.data
 * @date 2019年03月16日 9:56
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class User {
    private String isSuccess;//状态
    private String userID;//用户ID
    private String userName;//用户名字
    private String apiKey;//用户密匙
    private String userBalance;//用户余额
    private Map<String,Account> account;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(String userBalance) {
        this.userBalance = userBalance;
    }

    public Map<String,Account> getAccount() {
        return account;
    }

    public void setAccount(Map<String,Account> account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "isSuccess='" + isSuccess + '\'' +
                ", userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", userBalance='" + userBalance + '\'' +
                ", account=" + account.toString() +
                '}';
    }
}
