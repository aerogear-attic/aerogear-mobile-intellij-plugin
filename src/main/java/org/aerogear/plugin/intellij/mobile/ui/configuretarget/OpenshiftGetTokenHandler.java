package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import javax.swing.JButton;

public interface OpenshiftGetTokenHandler {
    String getUrlValue();
    String getLoginValue();
    JButton getGetTokenBtn();
    String getPasswordValue();
    boolean getTlsEnabledValue();
    String getTokenValue();
    String getNamespaceValue();
}
