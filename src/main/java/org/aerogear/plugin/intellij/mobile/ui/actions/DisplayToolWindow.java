package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;

import java.util.Objects;

public class DisplayToolWindow extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        ToolWindow toolWindow = ToolWindowManager.getInstance(Objects.requireNonNull(e.getProject())).getToolWindow(Constants.AEROGEAR_MOBILE);
        toolWindow.activate(null);
    }
}
