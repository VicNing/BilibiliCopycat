package com.vicning.android.bibilicopycat.network;

import com.vicning.android.bibilicopycat.model.entity.JJInfos;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Neil on 2016/8/8.
 */
public interface JJServices {
    @GET("Api/AvToCid/{aid}/1/")
    Observable<ResponseBody> getJJInfos(@Path("aid") String aid);
}
