package com.cxria.messageintercept.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by whr on 17-7-3.
 */

class NetUtil {
    private static final NetUtil ourInstance = new NetUtil();
    private final Retrofit mRetrofit;

    static NetUtil getInstance() {
        return ourInstance;
    }

    private NetUtil() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        GsonConverterFactory factory = GsonConverterFactory.create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(factory)
                .client(okHttpClient)
                .build();
    }

    public Retrofit initRetrofit() {
        return mRetrofit;
    }
}
