package com.vicning.android.bibilicopycat.network;

import com.vicning.android.bibilicopycat.model.entity.Video;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Neil on 2016/8/9.
 */
public interface InterfaceServices {
    @GET("playurl")
    Observable<Video> getVideoStream(@QueryMap Map<String, String> queryMap);
}
