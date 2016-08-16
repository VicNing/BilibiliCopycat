package com.vicning.android.bibilicopycat.network;

import android.support.annotation.Nullable;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Neil on 2016/8/10.
 */
public class RetrofitClientFactory {
    public static Retrofit createClient(String baseurl,
                                        @Nullable Converter.Factory convertyFactory,
                                        @Nullable CallAdapter.Factory adapterFactory) {
        if (convertyFactory == null && adapterFactory == null) {
            return new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .build();
        } else if (convertyFactory == null) {
            return new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addCallAdapterFactory(adapterFactory)
                    .build();
        } else if (adapterFactory == null) {
            return new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(convertyFactory)
                    .build();
        }
        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(convertyFactory)
                .addCallAdapterFactory(adapterFactory)
                .build();
    }

    public static Retrofit createClientwGsonAndRx(String baseurl) {
        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
