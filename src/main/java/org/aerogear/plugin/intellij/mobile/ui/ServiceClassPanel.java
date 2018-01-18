package org.aerogear.plugin.intellij.mobile.ui;

import com.intellij.ui.components.JBPanel;
import org.aerogear.plugin.intellij.mobile.ui.cmd.services.ServiceClass;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ServiceClassPanel extends JBPanel {
    private JLabel serviceHeader;
    private JTextArea serviceDescription;
    private JPanel servicePanel;
    private ImageIcon serviceIcon;
    private URL imgSrc;

    public ServiceClassPanel(ServiceClass service) {
        setLayout(new GridLayout(0, 1));


        serviceHeader = new JLabel(service.getSpec().getExternalMetadata().getDisplayName());
        serviceHeader.setIconTextGap(10);

        serviceDescription = new JTextArea("123123");
        serviceDescription.setLineWrap(true);
        serviceDescription.setWrapStyleWord(true);
        serviceDescription.setEditable(false);

        add(serviceHeader);
        add(serviceDescription);
    }
}
