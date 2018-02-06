package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.aerogear.plugin.intellij.mobile.ui.certconfiguration.PinnedCertDialog;

public class ViewCertConfiguration extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e){
        new PinnedCertDialog().show();
    }
}
