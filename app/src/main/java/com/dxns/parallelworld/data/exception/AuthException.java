package com.dxns.parallelworld.data.exception;

/**
 * 自定义异常
 * @author kingty
 * @title AuthException
 * @description
 * @modifier
 * @date
 * @since 15/6/18 下午5:21
 */
public class AuthException extends RuntimeException {

    public AuthException(Throwable throwable) {
        super(throwable);
    }
}
