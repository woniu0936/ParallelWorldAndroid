package com.dxns.data.api;

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


    public static Services getApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://parallelworld.kingty.club")
                .build();

        Services api = restAdapter.create(Services.class);

        return api;
    }

}
