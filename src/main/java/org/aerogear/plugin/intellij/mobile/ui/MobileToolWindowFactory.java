package org.aerogear.plugin.intellij.mobile.ui;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.*;
import org.aerogear.plugin.intellij.mobile.ui.cmd.CommandBuilder;
import org.aerogear.plugin.intellij.mobile.ui.cmd.services.MobileServices;
import org.aerogear.plugin.intellij.mobile.ui.cmd.services.ServiceClass;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class MobileToolWindowFactory implements ToolWindowFactory {

    private JPanel mobileToolWindowContent;
    private JList mobileServiceList;
    private ToolWindow mobileToolWindow;


    public MobileToolWindowFactory() {}

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        mobileToolWindow = toolWindow;
        mobileToolWindowContent.setLayout(new GridLayout(0, 1));
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        createServiceListDisplay(mobileToolWindowContent);
        JScrollPane scrollPane = new JBScrollPane(mobileToolWindowContent);

        Content content = contentFactory.createContent(scrollPane, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public void createServiceListDisplay(JPanel mobileToolWindowContent) {
        System.out.println("Creating service display...");
        CommandBuilder cb = new CommandBuilder();
        MobileServices serviceList = null;
        try {
            serviceList = cb.getServices();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

//        mobileServiceList = new JBList(Arrays.asList(serviceList.getItems()));
        // TODO: get the services into a for loop and display each in a panel
        ServiceClass[] services = serviceList.getItems();

        for(int i = 0; i < services.length; i++) {
            System.out.println(services[i].getSpec().getExternalMetadata().getDisplayName());
            mobileToolWindowContent.add(new ServiceClassPanel(services[i]));
        }
//        mobileServiceList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        mobileServiceList.setVisibleRowCount(-1);
//        mobileServiceList.setCellRenderer(new ServiceClassRenderer());

    }
}