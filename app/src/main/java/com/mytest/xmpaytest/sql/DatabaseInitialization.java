package com.mytest.xmpaytest.sql;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mytest.xmpaytest.config.ConfigurationProperties;

/**
 * 数据库初始化
 * @author 王伟
 * @title DatabaseInitialization
 * @package com.mytest.xmpaytest.sql
 * @date 2019年03月11日 16:59
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class DatabaseInitialization extends SQLiteOpenHelper {
    /**上下文**/
    private Context context;

    public DatabaseInitialization(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        /**int version-当前数据库的版本号，可用于对数据库进行升级操作**/
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**初始化数据表**/
        for(String sql : ConfigurationProperties.INIT_TABLE){
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
