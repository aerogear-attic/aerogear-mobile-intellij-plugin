package org.aerogear.plugin.intellij.mobile.ui;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ServiceListPane extends JBScrollPane {

    public ServiceListPane(ServiceClass[] scl) {
        JBPanel view = new JBPanel();
        view.setLayout(new BoxLayout(view, BoxLayout.PAGE_AXIS));
        setViewportView(view);
        view.withBackground(Color.WHITE);
        for (ServiceClass sc : scl) {
            view.add(createServicePanel(sc));
        }
    }

    public JPanel createServicePanel(ServiceClass sc) {
        ServicePanel svcPanel = new ServicePanel();
        JLabel serviceInfo = svcPanel.getServiceInfo();

        URL imgSrc = getClass().getClassLoader().getResource("icons/services/" + sc.getExternalName() + ".png");
        ImageIcon serviceIcon = new ImageIcon(imgSrc);
        serviceInfo.setIcon(serviceIcon);
        serviceInfo.setText(sc.getDisplayName());
        serviceInfo.setIconTextGap(10);

        return svcPanel;
    }
}
