package com.ning.core.exception;

/**
 * 令牌失效
 */
public class TokenUnavailable extends RuntimeException {

    public TokenUnavailable() {
    }

    public TokenUnavailable(String message) {
        super(message);
    }
}
