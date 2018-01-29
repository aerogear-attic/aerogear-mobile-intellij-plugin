package org.aerogear.plugin.intellij.mobile.ui.settings;


import com.intellij.openapi.ui.TextFieldWithBrowseButton;


public class SettingsPanel extends javax.swing.JPanel {

    public SettingsPanel() {
        initComponents();
    }

    private void initComponents() {


        projectSettingsHeader = new javax.swing.JPanel();
        headerText = new javax.swing.JLabel();
        headerSeparator = new javax.swing.JSeparator();
        projectSettings = new javax.swing.JPanel();
        sdkConfigLabel = new javax.swing.JLabel();
        sdkConfigValue = new TextFieldWithBrowseButton();

        headerText.setText("Project settings");

        javax.swing.GroupLayout projectSettingsHeaderLayout = new javax.swing.GroupLayout(projectSettingsHeader);
        projectSettingsHeader.setLayout(projectSettingsHeaderLayout);
        projectSettingsHeaderLayout.setHorizontalGroup(
                projectSettingsHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(projectSettingsHeaderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headerText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(headerSeparator)
                                .addContainerGap())
        );
        projectSettingsHeaderLayout.setVerticalGroup(
                projectSettingsHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(headerText)
                        .addComponent(headerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout projectSettingsLayout = new javax.swing.GroupLayout(projectSettings);
        projectSettings.setLayout(projectSettingsLayout);
        projectSettingsLayout.setHorizontalGroup(
                projectSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(projectSettingsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(sdkConfigValue, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                                .addContainerGap())
        );
        projectSettingsLayout.setVerticalGroup(
                projectSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(projectSettingsLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(sdkConfigValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(28, Short.MAX_VALUE))
        );

        sdkConfigLabel.setText("SDK config path");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(projectSettingsHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(sdkConfigLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(projectSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(projectSettingsHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(projectSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(sdkConfigLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>

    private javax.swing.JSeparator headerSeparator;
    private javax.swing.JLabel headerText;
    private javax.swing.JPanel projectSettings;
    private javax.swing.JPanel projectSettingsHeader;
    private javax.swing.JLabel sdkConfigLabel;
    private TextFieldWithBrowseButton sdkConfigValue;

    public TextFieldWithBrowseButton getSdkConfigValue() {
        return sdkConfigValue;
    }

}