package com.mytest.xmpaytest.service;

import android.content.Context;
import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.pojo.HttpRespons;
import com.mytest.xmpaytest.thread.UpdateConnectionThread;
import com.mytest.xmpaytest.util.HttpRequester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 更新监听状态
 *
 * @author 王伟
 * @title UpdateConnectionService
 * @package com.mytest.mytest.util
 * @date 2019年03月06日 22:05
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class UpdateConnectionService {
    /**
     * 线程池
     */
    static ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10);
    /**
     * 启动定时线程
     */
    public void init(){
        service.scheduleAtFixedRate(
                new UpdateConnectionThread(), 1,
                2, TimeUnit.SECONDS);
    }
}
