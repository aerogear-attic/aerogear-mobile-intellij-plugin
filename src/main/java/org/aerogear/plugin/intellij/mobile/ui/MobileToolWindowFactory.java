package org.aerogear.plugin.intellij.mobile.ui;


import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.content.*;
import org.aerogear.plugin.intellij.mobile.api.*;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.ui.servicecatalog.ServiceListPane;

import java.awt.*;

public class MobileToolWindowFactory implements ToolWindowFactory {

    private JBPanel mobileToolWindowContent;
    private ToolWindow mobileToolWindow;


    public MobileToolWindowFactory() {
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow){
        mobileToolWindow = toolWindow;
        mobileToolWindowContent = new JBPanel();
        mobileToolWindowContent.setLayout(new GridLayout(0, 1));
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        MobileServices serviceList;
        try {
            serviceList = new MobileAPI().getServices();
            if (serviceList != null) {
                mobileToolWindowContent.add(new ServiceListPane(serviceList.getItems()));
            } else {
                Notifications.Bus.notify(new Notification("AerogearMobileNotifications", "Mobile Services Unavailable", "There are no mobile services available", NotificationType.WARNING));
            }
        } catch (CLIException e) {
            Notifications.Bus.notify(new Notification("AerogearMobileNotifications", "Mobile CLI Exception", e.toString() + e.getCause(), NotificationType.ERROR));
        }

        Content content = contentFactory.createContent(mobileToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }
}