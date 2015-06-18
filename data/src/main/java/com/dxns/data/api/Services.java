package com.dxns.data.api;

import com.dxns.data.model.StatuData;

import retrofit.http.GET;
import rx.Observable;

/**
 * @author kingty
 * @title Api
 * @description
 * @modifier
 * @date
 * @since 15/6/17 下午10:47
 */
public interface Services {
    //@Field("emial") String email, @Field("password") String password
    @GET("/user.html")
    Observable<StatuData> Login();
}
