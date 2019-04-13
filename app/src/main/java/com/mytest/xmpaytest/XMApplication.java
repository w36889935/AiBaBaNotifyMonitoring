package com.mytest.xmpaytest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

/**
 * 类说明
 *
 * @author 王伟
 * @title XMApplication
 * @package com.mytest.xmpaytest
 * @date 2019年03月11日 13:43
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class XMApplication extends Application {

    @SuppressLint("StaticFieldLeak") private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        QMUISwipeBackActivityManager.init(this);
    }

    @Override
    public Resources.Theme getTheme() {
        return super.getTheme();
    }
}
