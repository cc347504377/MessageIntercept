package com.cxria.messageintercept.net;

import okhttp3.ResponseBody;
import retrofit2.http.POST;

/**
 * Created by whr on 17-7-3.
 */

public interface Api {
    @POST("")
    ResponseBody senSms();
}
