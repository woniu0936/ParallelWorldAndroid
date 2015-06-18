package com.dxns.data.api;

import com.dxns.data.service.UserServices;

import retrofit.RestAdapter;

/**
 * @author kingty
 * @title Service
 * @description
 * @modifier
 * @date
 * @since 15/6/17 下午11:08
 */
public class Api {


    public static UserServices getUserApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://parallelworld.kingty.club")
                .build();

        UserServices api = restAdapter.create(UserServices.class);

        return api;
    }

}
