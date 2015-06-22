package com.dxns.parallelworld.data.api;

import android.widget.Toast;

import com.dxns.parallelworld.core.Database;
import com.dxns.parallelworld.core.ParallelwordApplacation;
import com.dxns.parallelworld.data.exception.MyErrorHandler;
import com.dxns.parallelworld.data.service.UserServices;
import com.dxns.parallelworld.util.ToastUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * @author kingty
 * @title Service
 * @description
 * @modifier
 * @date
 * @since 15/6/17 下午11:08
 */
public class Api {


    private static  RestAdapter getAdapter(){
        //请求拦截器，便于在每个请求上面添加公共的参数

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                String userId = Database.getSharedPreferences().getString("userId","");

                if(userId.equals("")){
                    ToastUtils.show("请登录", Toast.LENGTH_SHORT);
                    //此处跳转登录界面
                }else {
                    request.addHeader("v", ParallelwordApplacation.getPackageInfo().versionName);
                    request.addHeader("device", "android");
                    request.addHeader("userId", userId);
                }

            }
        };
        //设置把所有的json都转换成小写
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://parallelworld.kingty.club")
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gson))
                .setErrorHandler(new MyErrorHandler())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();


        return restAdapter;
    }

    public static UserServices getUserApi() {


        UserServices api = getAdapter().create(UserServices.class);

        return api;
    }

}
