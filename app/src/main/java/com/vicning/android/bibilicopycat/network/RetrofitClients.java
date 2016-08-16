package com.vicning.android.bibilicopycat.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Neil on 2016/8/4.
 */
public class RetrofitClients {
    public static final String APP_BASEURL = "http://app.bilibili.com";
    public static final String BANGUMI_BASEURL = "http://bangumi.bilibili.com";
    public static final String API_BASEURL = "http://api.bilibili.com";
    public static final String BILIBILI_JJ_URL = "http://www.bilibilijj.com";
    public static final String INTERFACE_BASEURL = "http://interface.bilibili.com";
    public static final String COMMENT_BASEURL = "http://comment.bilibili.com";

    public static Retrofit retrofitAPP;
    public static Retrofit retrofitBangumi;
    public static Retrofit retrofitAPI;
    public static Retrofit retrofitJJ;
    public static Retrofit retrofitInterface;
    public static Retrofit retrofitComment;

    public static Retrofit getRetrofitAPP() {
        if (retrofitAPP == null) {
            synchronized (RetrofitClients.class) {
                if (retrofitAPP == null) {
                    retrofitAPP = RetrofitClientFactory.createClientwGsonAndRx(APP_BASEURL);
                }
            }
        }
        return retrofitAPP;
    }

    public static Retrofit getRetrofitAPI() {
        if (retrofitAPI == null) {
            synchronized (RetrofitClients.class) {
                if (retrofitAPI == null) {
                    retrofitAPI = RetrofitClientFactory.createClientwGsonAndRx(API_BASEURL);
                }
            }
        }
        return retrofitAPI;
    }

    public static Retrofit getRetrofitJJ() {
        if (retrofitJJ == null) {
            synchronized (RetrofitClients.class) {
                if (retrofitJJ == null) {
                    retrofitJJ = RetrofitClientFactory.createClientwGsonAndRx(BILIBILI_JJ_URL);
                }
            }
        }
        return retrofitJJ;
    }

    public static Retrofit getRetrofitBangumi() {
        if (retrofitBangumi == null) {
            synchronized (RetrofitClients.class) {
                if (retrofitBangumi == null) {
                    retrofitBangumi = RetrofitClientFactory.createClientwGsonAndRx(BANGUMI_BASEURL);
                }
            }
        }
        return retrofitBangumi;
    }

    public static Retrofit getRetrofitInterface() {
        if (retrofitInterface == null) {
            synchronized (RetrofitClients.class) {
                if (retrofitInterface == null) {
                    retrofitInterface = RetrofitClientFactory.createClient(INTERFACE_BASEURL,
                            SimpleXmlConverterFactory.create(),
                            RxJavaCallAdapterFactory.create());
                }
            }
        }
        return retrofitInterface;
    }

    public static Retrofit getRetrofitComment() {
        if (retrofitComment == null) {
            synchronized (RetrofitClients.class) {
                if (retrofitComment == null) {
                    retrofitComment = RetrofitClientFactory.createClient(COMMENT_BASEURL,
                            null,
                            RxJavaCallAdapterFactory.create());
                }
            }
        }
        return retrofitComment;
    }
}
