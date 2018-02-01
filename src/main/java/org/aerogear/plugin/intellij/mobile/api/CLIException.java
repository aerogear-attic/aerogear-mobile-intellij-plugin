package org.aerogear.plugin.intellij.mobile.api;

public class CLIException extends RuntimeException {
    public CLIException(String message, Throwable cause) {
        super(message, cause);
    }
    public CLIException(String message) {
        super(message);
    }
}
