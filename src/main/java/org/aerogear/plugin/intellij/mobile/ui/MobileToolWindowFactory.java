package org.aerogear.plugin.intellij.mobile.ui;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.*;
import org.aerogear.plugin.intellij.mobile.api.Client;
import org.aerogear.plugin.intellij.mobile.api.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.api.models.ServiceClass;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

        JScrollPane serviceListDisplay = createServiceListDisplay();
        mobileToolWindowContent.add(serviceListDisplay);

        Content content = contentFactory.createContent(mobileToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public JScrollPane createServiceListDisplay() {
        JBPanel serviceClassListContainer = new JBPanel();
        serviceClassListContainer.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane = new JBScrollPane(serviceClassListContainer);

        Client cb = new Client();
        MobileServices serviceList = null;
        try {
            serviceList = cb.getServices();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ServiceClass[] services = serviceList.getItems();

        for(int i = 0; i < services.length; i++) {
            serviceClassListContainer.add(new ServiceClassPanel(services[i]));
        }
        return scrollPane;
    }
}