package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.aerogear.plugin.intellij.mobile.api.Client;

import java.io.IOException;

public class DisplayToolWindow extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        ToolWindow toolWindow = ToolWindowManager.getInstance(e.getProject()).getToolWindow("Aerogear Mobile");
        Client cb = new Client();
        toolWindow.activate(null);
        try {
            cb.getServices();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
