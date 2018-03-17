package com.wordstalk.legal.drive.utils.exception;

/**
 * Created by y on 2018/1/16.
 */
public class SessionTimeoutException extends RuntimeException {

    public SessionTimeoutException() {
    }

    public SessionTimeoutException(String message) {
        super(message);
    }

    public SessionTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionTimeoutException(Throwable cause) {
        super(cause);
    }

    public SessionTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
