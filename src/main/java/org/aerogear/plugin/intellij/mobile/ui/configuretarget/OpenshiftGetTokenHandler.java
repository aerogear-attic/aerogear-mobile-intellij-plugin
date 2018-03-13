package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public interface OpenshiftGetTokenHandler {
    JTextField getUrlValue();
    JTextField getLoginValue();
    JButton getGetTokenBtn();
    JTextField getPasswordValue();
    JCheckBox getTlsEnabledValue();
    JTextField getTokenValue();
    JTextField getNamespaceValue();
}
