package com.cxria.messageintercept.components;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cxria.messageintercept.R;
import com.cxria.messageintercept.Url;
import com.cxria.messageintercept.db.SqlUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        //初始化
        SqlUtil.getInstance(Url.mSqlUrl, Url.mSqlName, Url.mSqlPassword);
    }
}
