package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

public interface OpenshiftGetTokenHandler {
    String getUrlValue();
    String getLoginValue();
    String getPasswordValue();
    boolean getTlsEnabledValue();
    String getTokenValue();
    String getNamespaceValue();
}
