package com.cxria.messageintercept;

import android.util.Log;

/**
 * Created by whr on 16-12-28.
 */

public class LogUtil {

    public static void setLog(String log) {
        boolean stat = true;
        if (!stat)
            return;
        //规定每段显示的长度
        int LOG_MAX_LENGTH = 2000;
        String TAG = "TAG";
        if (log == null) {
            Log.i(TAG, "");
            return;
        }
        int strLength = log.length();
        int start = 0;
        int end = LOG_MAX_LENGTH;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.i(TAG + i, "\n" + log.substring(start, end));
                start = end;
                end = end + LOG_MAX_LENGTH;
            } else {
                Log.i(TAG, log.substring(start, strLength) );
                Log.d(TAG, "-------------------------------------------------------\n ");
                break;
            }
        }

    }
}
