package com.mytest.xmpaytest;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.data.Account;
import com.mytest.xmpaytest.data.User;
import com.mytest.xmpaytest.service.LoginServer;
import com.mytest.xmpaytest.sql.DatabaseInitialization;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isRecord(getConnection("Users"))){
            selete();
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }

        //按钮监控
        Button btn1 = (Button) findViewById(R.id.login);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(isEnabled()){
                    Login(mHandler);
                }else{
                    getNotification();
                    Toast.makeText(MainActivity.this,"请授权通知权限！",Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    //数据库查询
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

    //登陆
    private void Login(Handler mHandler){
        LoginServer LoginServer = new LoginServer(mHandler);
        EditText userName = findViewById(R.id.usename);//账号
        EditText passWord = (EditText)findViewById(R.id.password);//密码
        String message = ""; //消息
        message = LoginServer.Login(userName.getText().toString(),passWord.getText().toString());
        if(message.length()>1){
            new QMUITipDialog.Builder(MainActivity.this)
                    .setIconType(3)
                    .setTipWord(message)
                    .create(true)
                    .show();
        }
    }

    //初始数据
    private void initData(User user){
        SQLiteDatabase sqliteUsersDatabase = getConnection("Users");
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

    //判断是否打开了通知监听权限
    private boolean isEnabled() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //获取监听权限
    private void getNotification(){
        try {
            Intent intent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            } else {
                intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //判断是否存在账号记录
    private Boolean isRecord(SQLiteDatabase sqliteDatabase){
        Cursor cursor = sqliteDatabase.query("Users", new String[] { "ID",
                "userID"},null, null, null, null, null);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    //返回连接
    private SQLiteDatabase getConnection(String tableName){
        SQLiteOpenHelper dbHelper = new DatabaseInitialization(MainActivity.this,tableName,null,1);
        return dbHelper.getReadableDatabase();
    }
}