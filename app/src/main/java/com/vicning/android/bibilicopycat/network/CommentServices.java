package com.vicning.android.bibilicopycat.network;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Neil on 2016/8/10.
 */
public interface CommentServices {
    @GET("{cid}.xml")
    Observable<ResponseBody> getComments(@Path("cid") String cid);
}
