package com.dxns.parallelworld.data.exception;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * 处理自己的网络异常
 * @author kingty
 * @title MyErrorHandler
 * @description
 * @modifier
 * @date
 * @since 15/6/22 下午7:17
 */
public class MyErrorHandler implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();
        if (r != null && r.getStatus() == 401) {
            return new AuthException(cause);
        }
        return cause;
    }
}
