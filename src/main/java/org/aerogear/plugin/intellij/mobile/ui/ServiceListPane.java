package org.aerogear.plugin.intellij.mobile.ui;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ServiceListPane extends JBScrollPane {

    public ServiceListPane(ServiceClass[] scl) throws CLIException {
        JBPanel view = new JBPanel();
        view.setLayout(new BoxLayout(view, BoxLayout.PAGE_AXIS));
        setViewportView(view);
        view.withBackground(Color.WHITE);
        for (ServiceClass sc : scl) {
            view.add(createServicePanel(sc));
        }
    }

    public JPanel createServicePanel(ServiceClass sc) throws CLIException {
        ServicePanel svcPanel = new ServicePanel();
        JLabel serviceInfo = svcPanel.getServiceInfo();
        URL imgSrc;

        try {
            imgSrc = new URL(sc.getSpec().getExternalMetadata().getImageUrl());
        } catch (MalformedURLException e) {
            throw new CLIException("Failed to retrieve service icon", e.getCause());
        }

        if (imgSrc != null) {
            ImageIcon serviceIcon = new ImageIcon(imgSrc);
            Image img = serviceIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            serviceInfo.setIcon(new ImageIcon(img));
            serviceInfo.setIconTextGap(10);
        }

        serviceInfo.setText(sc.getDisplayName());

        svcPanel.getDeploy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new DeployServiceDialog(sc).show();
            }
        });

        return svcPanel;
    }
}
