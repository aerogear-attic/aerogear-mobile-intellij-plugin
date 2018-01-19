package org.aerogear.plugin.intellij.mobile.ui;

import com.intellij.ui.components.JBPanel;
import org.aerogear.plugin.intellij.mobile.api.models.ServiceClass;
import org.aerogear.plugin.intellij.mobile.api.models.Spec;

import javax.swing.*;
import java.net.URL;

public class ServiceClassPanel extends JBPanel {
    private JLabel serviceHeader;
    private JTextArea serviceDescription;
    private ImageIcon serviceIcon;
    private URL imgSrc;

    public ServiceClassPanel(ServiceClass service) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        Spec serviceSpec = service.getSpec();
        serviceHeader = new JLabel(serviceSpec.getExternalMetadata().getDisplayName());
        serviceHeader.setIconTextGap(10);

        imgSrc = getClass().getClassLoader().getResource("icons/services/" + serviceSpec.getExternalName() + ".png");
        serviceIcon = new ImageIcon(imgSrc);
        serviceHeader.setIcon(serviceIcon);
        serviceHeader.setText(serviceSpec.getExternalMetadata().getDisplayName());
        add(serviceHeader);

        serviceDescription = new JTextArea(serviceSpec.getDescription());
        serviceDescription.setLineWrap(true);
        serviceDescription.setWrapStyleWord(true);
        serviceDescription.setEditable(false);
        add(serviceDescription);
    }
}
