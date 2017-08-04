package com.cxria.messageintercept.net;

/**
 * Created by whr on 17-7-3.
 */

class SmsNet {
    private static final SmsNet ourInstance = new SmsNet();

    static SmsNet getInstance() {
        return ourInstance;
    }

    private SmsNet() {
        Api api = NetUtil.getInstance().initRetrofit().create(Api.class);
    }
}
