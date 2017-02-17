package com.hermes.core.domain.accounts;

/**
 * Created by ivan on 08.11.16.
 */
public class NoSuchRoleException extends Exception {
    public NoSuchRoleException() {
    }

    public NoSuchRoleException(String message) {
        super(message);
    }

    public NoSuchRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRoleException(Throwable cause) {
        super(cause);
    }

    public NoSuchRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
