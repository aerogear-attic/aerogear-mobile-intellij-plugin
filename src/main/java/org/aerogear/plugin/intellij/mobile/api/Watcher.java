package org.aerogear.plugin.intellij.mobile.api;

public interface Watcher {
    void onError(Exception e);
    void onSuccess(Object obj);
}
