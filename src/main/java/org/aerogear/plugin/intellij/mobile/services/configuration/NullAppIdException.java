package org.aerogear.plugin.intellij.mobile.services.configuration;

public class NullAppIdException extends RuntimeException {
    public NullAppIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullAppIdException(String message) {
        super(message);
    }
}