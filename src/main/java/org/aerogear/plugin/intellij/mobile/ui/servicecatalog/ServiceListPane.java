package org.aerogear.plugin.intellij.mobile.ui.servicecatalog;

import com.intellij.openapi.project.Project;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceListPane extends JBScrollPane {
    private final Project project;

    public ServiceListPane(Project project, ServiceClass[] scl) throws CLIException {
        this.project = project;

        JBPanel view = new JBPanel();
        view.setLayout(new BoxLayout(view, BoxLayout.PAGE_AXIS));
        setViewportView(view);
        view.withBackground(JBColor.WHITE);

        for (ServiceClass sc : scl) {
            view.add(createServicePanel(sc));
        }
    }

    private JPanel createServicePanel(ServiceClass sc) throws CLIException {
        ServicePanel svcPanel = new ServicePanel();
        JLabel serviceInfo = svcPanel.getServiceInfo();
        URL imgSrc = null;

        try {
            String imagerURL = sc.getSpec().getExternalMetadata().getImageUrl();
            if (null != imagerURL) {
                imgSrc = new URL(imagerURL);
            }
        } catch (MalformedURLException e) {
            throw new CLIException("Failed to retrieve service icon ", e.getCause());
        }

        if (imgSrc != null) {
            ImageIcon serviceIcon = new ImageIcon(imgSrc);
            Image img = serviceIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            serviceInfo.setIcon(new ImageIcon(img));
            serviceInfo.setIconTextGap(10);
        }

        serviceInfo.setText(sc.getDisplayName());

        svcPanel.getDeploy().addActionListener(actionEvent -> new DeployServiceDialog(this.project, sc).show());

        return svcPanel;
    }
}
