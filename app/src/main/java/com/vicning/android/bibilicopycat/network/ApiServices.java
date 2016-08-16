package com.vicning.android.bibilicopycat.network;


import com.vicning.android.bibilicopycat.model.entity.VideoComment;
import com.vicning.android.bibilicopycat.model.entity.VideoInfo;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Neil on 2016/8/8.
 */
public interface ApiServices {

    @GET("x/reply")
    Observable<VideoComment> getVideoComment(@Query("oid") String oid,
                                             @Query("pn") int pn,
                                             @Query("ps") int ps,
                                             @Query("sort") int sort,
                                             @Query("type") int type);

    @GET("view")
    Observable<VideoInfo> getVideoInfo(@QueryMap Map<String, String> queryMap);

}
