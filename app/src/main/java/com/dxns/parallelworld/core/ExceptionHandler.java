package com.dxns.parallelworld.core;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.dxns.parallelworld.R;


import com.dxns.parallelworld.data.exception.AuthException;
import com.dxns.parallelworld.util.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * 公共的异常统一此类处理
 * @author kingty
 * @title ExceptionHandler
 * @description
 * @modifier
 * @date
 * @since 15/6/18 下午5:19
 */
public class ExceptionHandler {

    public static void handleException(final Context context, final Throwable e) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            AndroidSchedulers.mainThread()
                    .createWorker()
                    .schedule(new Action0() {
                        @Override
                        public void call() {
                            handleException(context, e);
                        }
                    });
            return;
        }
        if (context == null || e == null) {
            return;
        }
        if(e instanceof OutOfMemoryError){
            ToastUtils.show(ParallelwordApplacation.get().getResources().getString(R.string.outof_memory), Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
        if(e instanceof AuthException){
            //处理验证异常
            e.printStackTrace();
        }

        e.printStackTrace();
        //添加其他异常处理
    }


}
