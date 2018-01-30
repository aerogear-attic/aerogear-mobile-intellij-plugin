package org.aerogear.plugin.intellij.mobile.ui.sdkconfig;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class ViewSDKConfigDialog extends DialogWrapper {
    private String sdkConfig;

    public ViewSDKConfigDialog(String sdkConfig) {
        super(null);
        this.sdkConfig = sdkConfig;

        init();
        setTitle("SDK config");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        ViewSDKConfigPanel viewPanel = new ViewSDKConfigPanel();
        JTextArea contentArea = viewPanel.getSdkConfigDisplay();
        contentArea.append(this.sdkConfig);
        contentArea.setEditable(false);
        JScrollPane scrollPane = viewPanel.getScrollPane();
        scrollPane.setPreferredSize(contentArea.getPreferredSize());
        return viewPanel;
    }
}
