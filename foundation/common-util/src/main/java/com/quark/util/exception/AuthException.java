package com.quark.util.exception;

/**
 * 鉴权异常
 */
public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
