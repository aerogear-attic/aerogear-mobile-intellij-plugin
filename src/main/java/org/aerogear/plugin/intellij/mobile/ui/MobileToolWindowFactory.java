package org.aerogear.plugin.intellij.mobile.ui;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.content.*;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.ui.certconfiguration.CertPane;
import org.aerogear.plugin.intellij.mobile.ui.certconfiguration.CertPanel;
import org.aerogear.plugin.intellij.mobile.ui.servicecatalog.ServiceListPane;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunner;
import org.aerogear.plugin.intellij.mobile.api.CliRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;


import javax.swing.*;
import java.awt.*;

public class MobileToolWindowFactory implements ToolWindowFactory {
    private JBPanel mobileToolWindowContent;
    MobileNotificationsService notifier;
    private CLIRunner cliRunner = new CliRunnerImpl();


    public MobileToolWindowFactory() {
        notifier = ServiceManager.getService(MobileNotificationsService.class);
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow){
        mobileToolWindowContent = new JBPanel();
        mobileToolWindowContent.setLayout(new GridLayout(0, 1));
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        MobileServices serviceList;
        try {
            serviceList = new MobileAPI(cliRunner).getServices();
            if (serviceList.getItems() != null) {
                mobileToolWindowContent.add(new ServiceListPane(serviceList.getItems()));
            } else {
                notifier.notifyInformation("Mobile Services Unavailable", "There are no mobile services available");
            }
        } catch (CLIException e) {
            notifier.notifyError("Mobile CLI Exception", e.toString());
        }

        // add cert config panel to plugin
        createCertConfigContent();

        Content content = contentFactory.createContent(mobileToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public void createCertConfigContent(){
        try {
            mobileToolWindowContent.add(new CertPane());
        }catch (Exception e){
            notifier.notifyError("Error : ",e.toString());
        }
    }
}
