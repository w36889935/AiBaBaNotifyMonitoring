package com.mytest.xmpaytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mytest.xmpaytest.config.ConfigurationProperties;
import com.mytest.xmpaytest.service.UpdateConnection;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startHeartbeat();
    }

    //启动心跳
    private void startHeartbeat(){
        UpdateConnection updateConnection = new UpdateConnection();
        updateConnection.init();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();禁止其返回
    }
}
