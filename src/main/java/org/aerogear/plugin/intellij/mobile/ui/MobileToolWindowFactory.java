package org.aerogear.plugin.intellij.mobile.ui;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.content.*;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import java.awt.*;

public class MobileToolWindowFactory implements ToolWindowFactory {

    private JBPanel mobileToolWindowContent;
    private ToolWindow mobileToolWindow;


    public MobileToolWindowFactory() {}

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        mobileToolWindow = toolWindow;
        mobileToolWindowContent = new JBPanel();
        mobileToolWindowContent.setLayout(new GridLayout(0, 1));
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        MobileServices serviceList = new MobileAPI().getServices();
        mobileToolWindowContent.add(new ServiceListPane(serviceList.getItems()));

        Content content = contentFactory.createContent(mobileToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }
}