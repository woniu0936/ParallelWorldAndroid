package com.dxns.parallelworld.util;

import android.os.Looper;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.dxns.parallelworld.core.ParallelwordApplacation;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by panwenye on 14-11-9.
 */
public class ToastUtils {

    public static void show(@StringRes int msgResId, final int length) {
        show(ParallelwordApplacation.get().getString(msgResId),length);
    }

    public static void show(final String msg, final int length) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                @Override
                public void call() {
                    show(msg, length);
                }
            });
            return;
        }
        Toast.makeText(ParallelwordApplacation.get(), msg, length).show();
    }

    public static void showWhenDebug(final String msg, final int length) {
        if (ParallelwordApplacation.DEBUG) {
            show(msg, length);
        }
    }
}
