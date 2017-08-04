package com.cxria.messageintercept.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.cxria.messageintercept.Url;
import com.cxria.messageintercept.db.SqlUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //解析短信
        Object[] pduses = (Object[]) intent.getExtras().get("pdus");
        for (Object pdus : pduses) {
            byte[] pdusmessage = (byte[]) pdus;
            SmsMessage sms = SmsMessage.createFromPdu(pdusmessage);
            String mobile = sms.getOriginatingAddress();//发送短信的手机号码
            String content = sms.getMessageBody(); //短信内容

            //解析时间
            Date date = new Date(sms.getTimestampMillis());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);  //得到发送时间

            //解析验证码
            String code = "";
            try {
                int index = content.indexOf("验证码为") + 4;
                code = content.substring(index, index + 6);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.i("TAG", "手机号码:" + mobile + " code:" + code + " 时间:" + time);
            Toast.makeText(context, "手机号码:" + mobile + " code:" + code + " 时间:" + time, Toast.LENGTH_SHORT).show();

            //插入数据库
            SqlUtil sqlUtil = SqlUtil.getInstance(Url.mSqlUrl, Url.mSqlName, Url.mSqlPassword);
            sqlUtil.insert(Url.mSqlTableName, mobile, code, time, content);
        }
    }
}
