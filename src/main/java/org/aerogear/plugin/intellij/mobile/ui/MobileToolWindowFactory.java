package org.aerogear.plugin.intellij.mobile.ui;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.*;
import org.aerogear.plugin.intellij.mobile.ui.cmd.CommandBuilder;
import org.aerogear.plugin.intellij.mobile.ui.cmd.services.MobileServices;

import javax.swing.*;
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
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        CommandBuilder cb = new CommandBuilder();
        MobileServices serviceList = null;
        try {
            serviceList = cb.getServices();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        mobileServiceList = new JBList(Arrays.asList(serviceList.getItems()));
        mobileServiceList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        mobileServiceList.setLayoutOrientation(JBList.HORIZONTAL_WRAP);
        mobileServiceList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JBScrollPane(mobileServiceList);
        mobileToolWindowContent.add(listScroller);
        Content content = contentFactory.createContent(mobileToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }

}