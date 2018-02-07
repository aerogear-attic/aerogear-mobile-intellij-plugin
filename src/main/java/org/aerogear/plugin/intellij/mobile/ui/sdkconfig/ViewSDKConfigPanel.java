package org.aerogear.plugin.intellij.mobile.ui.sdkconfig;

import javax.swing.*;

public class ViewSDKConfigPanel extends javax.swing.JPanel {

    public ViewSDKConfigPanel() {
        initComponents();
    }

    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        sdkConfigDisplay = new javax.swing.JTextArea();

        setLayout(new java.awt.GridLayout());

        sdkConfigDisplay.setColumns(20);
        sdkConfigDisplay.setRows(5);
        scrollPane.setViewportView(sdkConfigDisplay);

        add(scrollPane);
    }


    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea sdkConfigDisplay;

    public JTextArea getSdkConfigDisplay() {
        return sdkConfigDisplay;
    }


    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}