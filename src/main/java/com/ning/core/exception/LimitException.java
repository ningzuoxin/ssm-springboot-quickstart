package com.ning.core.exception;

/**
 * API接口防刷异常
 */
public class LimitException extends RuntimeException {

    public LimitException(String message) {
        super(message);
    }
}
