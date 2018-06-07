package com.kedacom.mvvmdemo.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/6 14:47
 */
public class RetrofitHelper {

    private static final int DEFAULT_TIMEOUT = 8; //连接 超时的时间，单位：秒
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();
    private static RetrofitHelper retrofitHelper;
    private ApiService apiService;
    /**
     * 服务器地址
     */
    private static final String SERVER_URL = "http://www.kuaidi100.com/";
    private static final String URL_BASE = "https://news-at.zhihu.com/";

    public static RetrofitHelper getInstance() {
        return retrofitHelper == null ? retrofitHelper = new RetrofitHelper() : retrofitHelper;
    }

    private RetrofitHelper() {
        // 初始化Retrofit
        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()) // json解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

}
