package com.mytest.xmpaytest.util;

/**
 * 检验工具
 *
 * @author 王伟
 * @title CheckTools
 * @package com.mytest.xmpaytest.util
 * @date 2019年03月15日 10:53
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class CheckTools {

    /**
     * 判断是否为空值
     * @param  param  待验证参数
     * @return boolean
     * @throws
     */
    public boolean isNullValue(String param){
        if(param == null || "".equals(param)){
            return true;
        }else {
            return false;
        }
    }
}
