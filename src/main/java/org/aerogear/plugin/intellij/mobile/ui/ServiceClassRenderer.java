package org.aerogear.plugin.intellij.mobile.ui;

import org.aerogear.plugin.intellij.mobile.ui.cmd.services.ServiceClass;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ServiceClassRenderer extends JPanel implements ListCellRenderer<ServiceClass>{
    private JLabel serviceHeader;
    private JTextArea serviceDescription;
    private JPanel servicePanel;
    private ImageIcon serviceIcon;
    private URL imgSrc;

    public ServiceClassRenderer() {
        super();

        // TODO: Set Layout
        setLayout(new GridLayout(0,1, 0, 1));

        servicePanel = new JPanel(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        serviceHeader = new JLabel();
        serviceHeader.setIconTextGap(10);

        serviceDescription = new JTextArea();
        serviceDescription.setLineWrap(true);
        serviceDescription.setWrapStyleWord(true);
        serviceDescription.setEditable(false);

        servicePanel.add(serviceHeader);
        servicePanel.add(serviceDescription);

        add(servicePanel);
    }

    public Component getListCellRendererComponent(JList<? extends ServiceClass> list,
                                                  ServiceClass serviceClass,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus)
    {
        // Set the service header which includes the service icon and name
        imgSrc = getClass().getClassLoader().getResource("icons/services/dh-unifiedpush-apb-20.png"); // TODO: Change to real images.
        serviceIcon = new ImageIcon(imgSrc);
        serviceHeader.setIcon(serviceIcon);

        serviceHeader.setText(serviceClass.getSpec().getExternalMetadata().getDisplayName());
        serviceHeader.setSize(new Dimension(Component.WIDTH, 5));
        serviceHeader.setPreferredSize(new Dimension(Component.WIDTH, 5));
        serviceHeader.setMaximumSize(new Dimension(Component.WIDTH, 5));

        // Set the service description
        serviceDescription.setText(serviceClass.getSpec().getDescription());

//        JPanel serviceCell = new JPanel();
//        serviceCell.add(new JLabel("ServiceHeader"));
//        serviceCell.add(new JLabel("ServiceDescription"));
//        return serviceCell;
//        System.out.println(index);
//        setText("<html>" + serviceClass.getSpec().getExternalMetadata().getDisplayName() + "<br>" + serviceClass.getSpec().getDescription() + "</html>");

//        System.out.println("Getting list cell renderer component");
//        setText(serviceClass.getSpec().getExternalName());
//
//        try {
//            icon = new ImageIcon(new URL(serviceClass.getSpec().getExternalMetadata().getImageUrl()));
//            Image scaledImage = icon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
//            icon = new ImageIcon(scaledImage);
//            setIcon(icon);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Icon: " + serviceClass.getSpec().getExternalMetadata().getImageUrl());
        return this;
    }
}
