package com.dxns.parallelworld.core;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 应用的applacation
 * @author kingty
 * @title ParallelwordApplacation
 * @description
 * @modifier
 * @date
 * @since 15/6/18 下午5:30
 */
public class ParallelwordApplacation extends android.support.multidex.MultiDexApplication {

    private static final String TAG  = "ParallelwordApplacation";
    private static ParallelwordApplacation application;
    private static PackageInfo packageInfo;
    public static ParallelwordApplacation get() {
        return application;
    }

    public static PackageInfo getPackageInfo() {
        return packageInfo;
    }

    /**
     * 标记是否输出debug信息
     */
    public static boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        application = this;
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Database.get();
    }
}
