package com.mytest.xmpaytest.data;

/**
 * 类说明
 *
 * @author 王伟
 * @title Account
 * @package com.mytest.xmpaytest.data
 * @date 2019年03月16日 9:57
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class Account {
    private String accountType;//支付类型
    private String accountNO;//支付账户
    private String AccountStatus;//状态

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNO() {
        return accountNO;
    }

    public void setAccountNO(String accountNO) {
        this.accountNO = accountNO;
    }

    public String getAccountStatus() {
        return AccountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        AccountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountType='" + accountType + '\'' +
                ", accountNO='" + accountNO + '\'' +
                ", AccountStatus='" + AccountStatus + '\'' +
                '}';
    }
}
