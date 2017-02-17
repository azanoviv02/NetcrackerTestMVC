package com.hermes.core.domain.accounts;

/**
 * Created by ivan on 08.11.16.
 */
public class LoginAlreadyTakenException extends Exception {
    public LoginAlreadyTakenException() {
    }

    public LoginAlreadyTakenException(String message) {
        super(message);
    }

    public LoginAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAlreadyTakenException(Throwable cause) {
        super(cause);
    }

    public LoginAlreadyTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
