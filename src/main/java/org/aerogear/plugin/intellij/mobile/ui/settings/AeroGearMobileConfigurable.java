package org.aerogear.plugin.intellij.mobile.ui.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class AeroGearMobileConfigurable implements Configurable {
    private SettingsPanel settingsPanel;
    private final AeroGearMobileConfiguration config;
    private final Project project;

    public AeroGearMobileConfigurable(Project project) {
        this.project = project;
        this.config = AeroGearMobileConfiguration.getInstance(project);
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "AeroGear Mobile";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsPanel = new SettingsPanel(project, config);
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        String configPath = this.config.getConfigPath();
        boolean configPathModified =  !settingsPanel.getConfigPath().equals(configPath);

        boolean targetConfigModified = !config.getTargetConfig().equals(settingsPanel.getTargetConfig());
        return configPathModified || targetConfigModified;
    }

    @Override
    public void apply() {
        System.out.println("apply");
        this.config.setConfigPath(this.settingsPanel.getConfigPath());
        this.config.setFromTargetConfig(this.settingsPanel.getTargetConfig());
    }

    @Override
    public void reset() {
        settingsPanel.resetFields(config);
    }
}
