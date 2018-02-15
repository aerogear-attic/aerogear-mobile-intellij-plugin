package org.aerogear.plugin.intellij.mobile.ui.sdkconfig;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ViewSDKConfigDialog extends DialogWrapper {
    private final String sdkConfig;

    public ViewSDKConfigDialog(String sdkConfig) {
        super(null);
        this.sdkConfig = sdkConfig;

        init();
        setTitle(Constants.SDK_CONFIG);
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
