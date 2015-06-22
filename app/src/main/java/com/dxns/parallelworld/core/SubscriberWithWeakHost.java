package com.dxns.parallelworld.core;


import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;

import rx.Subscriber;

/**
 * 名称: Subscriber基础类。不能声明为内部类
 * 用途：1.避免 Subscriber中不健壮代码造成程序崩溃
 * 2.避免和Activity等宿主生命周期交叉，影响内存回收
 * <p/>
 * <p/>
 * 简述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2014/12/10 10:22<br>
 * 改用doOnxx系列方法，方便catch到异常，避免错误事件循环冒泡的问题
 * 最近修改时间:2014/12/29 10:22<br>
 * 禁止作为非静态内部类使用，通过weakref引用宿主对象
 *
 * @author grenn
 * @since 2014/4/8
 */
public class SubscriberWithWeakHost<T, H> extends Subscriber<T> {
    WeakReference<H> mWeakRefHost;

    public SubscriberWithWeakHost(H host) {
        if (getClass().isAnonymousClass()) {
            throw new IllegalStateException("SubscriberWithWeakHost must not be declared as an anonymousClass");
        }
        if (getClass().isLocalClass()) {
            throw new IllegalStateException("SubscriberWithWeakHost must not be declared as an LocalClass");
        }
        if (getClass().isMemberClass() && !Modifier.isStatic(getClass().getModifiers())) {
            throw new IllegalStateException("SubscriberWithWeakHost must be declared as an static member class or single class");
        }
        if (host == null) {
            throw new IllegalArgumentException("host must not be null");
        }
        mWeakRefHost = new WeakReference<H>(host);
    }

    public boolean isHostExist() {
        return mWeakRefHost != null && mWeakRefHost.get() != null;
    }

    public H getHost() {
        if (isHostExist()) {
            return mWeakRefHost.get();
        }
        return null;
    }

    public void doOnCompleted() {

    }

    public void doOnError(Throwable e) {
    }

    public void doOnNext(T item) {

    }

    @Override
    final public void onCompleted() {
        try {
            doOnCompleted();
        } catch (Exception e) {
            ExceptionHandler.handleException(ParallelwordApplacation.get(), e);
        }
    }

    @Override
    final public void onError(Throwable e) {
        try {
            doOnError(e);
        } catch (Exception e2) {
            ExceptionHandler.handleException(ParallelwordApplacation.get(), e2);
            e2.printStackTrace();
        }
    }

    @Override
    final public void onNext(T t) {
        try {
            doOnNext(t);
        } catch (Exception e3) {
            ExceptionHandler.handleException(ParallelwordApplacation.get(), e3);

        }
    }

}
