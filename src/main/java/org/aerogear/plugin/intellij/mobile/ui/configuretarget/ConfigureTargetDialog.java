package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

public class ConfigureTargetDialog extends DialogWrapper {
    private AeroGearMobileConfiguration config;
    private ConfigureTargetPanel configureTargetPanel;

    public ConfigureTargetDialog(@Nullable Project project) {
        super(project);
        config = AeroGearMobileConfiguration.getInstance(project);
        init();
        setTitle(Constants.CONFIGURE_TARGET);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        configureTargetPanel = new ConfigureTargetPanel(config);
        return configureTargetPanel;
    }


    public TargetConfig getTargetConfig(){
        return configureTargetPanel.getTargetConfig();
    }
}
