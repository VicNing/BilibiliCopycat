package com.vicning.android.bibilicopycat.network;

import com.vicning.android.bibilicopycat.model.entity.Banners;
import com.vicning.android.bibilicopycat.model.entity.Recommends;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Neil on 2016/8/4.
 */
public interface AppServices {
    @GET("x/show/old")
    Observable<Recommends> getRecommends();

    @GET("x/banner")
    Observable<Banners> getBanners(@Query("plat") String plat,
                                   @Query("bulid") String build,
                                   @Query("channel") String channel);
}
