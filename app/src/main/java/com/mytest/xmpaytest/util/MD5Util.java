package com.mytest.xmpaytest.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 类说明
 * MD5加密类
 * @author 王伟
 * @title MD5Util
 * @package com.mytest.mytest.util
 * @date 2019年03月07日 15:38
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class MD5Util {
    public  void main(String[] args) {
        MD5("W190307-143807-10001-1.00-629206100011");
    }

    public  String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result.toUpperCase();
    }
}
