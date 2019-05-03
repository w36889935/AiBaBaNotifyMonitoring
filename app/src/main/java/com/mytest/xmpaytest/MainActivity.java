package com.mytest.xmpaytest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.data.Account;
import com.mytest.xmpaytest.data.User;
import com.mytest.xmpaytest.service.LoginServer;
import com.mytest.xmpaytest.sql.DatabaseInitialization;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.Map;

/**
 * 登陆主界面
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**获得用户连接对象**/
        SQLiteDatabase usersData = getConnection("Users");
        /**判断是否存在用户信息**/
        if(isRecord(usersData)){
            selete();
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }

        /**按钮监控**/
        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**登陆**/
               Login(mHandler);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2 || msg.what ==3) {
                Map<String ,Object> messages = (Map<String, Object>) msg.obj;
                new QMUITipDialog.Builder(MainActivity.this)
                        .setIconType(msg.what)
                        .setTipWord(messages.get("messags").toString())
                        .create(true)
                        .show();
                if( msg.what == 2){
                    initData((User)messages.get("user"));
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                    selete();
                }
            }
        }
    };

    /**
     * 登陆
     * @param mHandler 消息处理对象
     * @return 
     * @throws 
     **/
    private void Login(Handler mHandler){
        /**获得账号**/
        String userName = ((EditText)findViewById(R.id.usename)).getText().toString();
        /**获得密码**/
        String passWord = ((EditText)findViewById(R.id.password)).getText().toString();
        /**创建登陆服务**/
        LoginServer LoginServer = new LoginServer(mHandler,MainActivity.this);
        /**登陆**/
        LoginServer.Login(userName,passWord);
    }

    /**
     * 初始数据
     * @param user 用户实体对象
     * @return
     * @throws
     */
    private void initData(User user){
        /**获得用户连接对象**/
        SQLiteDatabase sqliteUsersDatabase = getConnection("Users");
        /**获得账户连接对象**/
        SQLiteDatabase sqliteAccountsDatabase = getConnection("Accounts");

        ContentValues values = new ContentValues();
        values.put("userID", user.getUserID());
        values.put("userName", user.getUserName());
        values.put("ApiKey", user.getApiKey());
        values.put("userBalance", user.getUserBalance());
        values.put("IsSuccess", user.getIsSuccess());
        sqliteUsersDatabase.insert("Users", null, values);

        ContentValues values2 = new ContentValues();
        Map<String,Account>  accounts = user.getAccount();
        for(String key : accounts.keySet()){
            values2.put("accountNO", accounts.get(key).getAccountNO());
            values2.put("accountType", accounts.get(key).getAccountType());
            values2.put("AccountStatus",accounts.get(key).getAccountStatus());
            sqliteAccountsDatabase.insert("Accounts", null, values2);
        }
    }

    /**
     * 方法说明 判断是否存在账号记录
     * @param
     * @return
     * @throws
     */
    private Boolean isRecord(SQLiteDatabase sqliteDatabase){
        /**查询用户表是否存在以登陆用户的信息**/
        Cursor cursor = sqliteDatabase.query("Users", new String[] { "ID",
                "userID"},null, null, null, null, null);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 方法说明 返回连接
     * @param
     * @return
     * @throws
     */
    private SQLiteDatabase getConnection(String tableName){
        SQLiteOpenHelper dbHelper = new DatabaseInitialization(MainActivity.this,tableName,null,1);
        return dbHelper.getReadableDatabase();
    }

    /**
     * 方法说明 数据库查询
     * @param
     * @return
     * @throws
     */
    private void selete(){
        SQLiteDatabase sqliteDatabase = getConnection("Users");
        Cursor cursor = sqliteDatabase.query("Users", new String[] { "userID",
                "apiKey","userBalance","userName"}, null, null, null, null, "ID desc","0,1");
        while (cursor.moveToNext()) {
            ConfigurationProperties.USER_ID = cursor.getString(cursor.getColumnIndex("userID"));
            ConfigurationProperties.APIKEY = cursor.getString(cursor.getColumnIndex("apiKey"));
        }

        SQLiteDatabase sqliteDatabaseAccounts = getConnection("Accounts");
        Cursor cursorAccounts = sqliteDatabaseAccounts.query("Accounts", new String[] { "accountType",
                "accountNO"}, "AccountStatus=?", new String[] { "1" }, null, null, "ID desc","0,2");
        while (cursorAccounts.moveToNext()) {
            if(cursorAccounts.getString(cursorAccounts.getColumnIndex("accountType")).equals("101")){
                ConfigurationProperties.ALI_ACCOUNTNO =  cursorAccounts.getString(cursorAccounts.getColumnIndex("accountNO"));
            }
        }
    }

}