package org.aerogear.plugin.intellij.mobile.ui;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.content.*;

import javax.swing.*;

public class MobileToolWindowFactory implements ToolWindowFactory {

    private JPanel mobileToolWindowContent;
    private ToolWindow mobileToolWindow;


    public MobileToolWindowFactory() {}

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        mobileToolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mobileToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }

}