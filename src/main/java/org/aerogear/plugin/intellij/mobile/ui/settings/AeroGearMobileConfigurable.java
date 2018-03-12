package org.aerogear.plugin.intellij.mobile.ui.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.aerogear.plugin.intellij.mobile.ui.configuretarget.Constants;
import org.aerogear.plugin.intellij.mobile.ui.configuretarget.TargetConfig;
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
        settingsPanel = new SettingsPanel(project);

        settingsPanel.setUrlValue(config.getTargetConfig().getUrl());
        settingsPanel.setLoginValue(config.getTargetConfig().getLogin());
        settingsPanel.setTlsEnabledValue(config.getTargetConfig().getTlsEnabled());
        settingsPanel.setTokenValue(config.getTargetConfig().getToken());
        settingsPanel.setNamespaceValue(config.getTargetConfig().getNamespace());
        settingsPanel.setPasswordNote(Constants.PASSWORD_NOTE);
        settingsPanel.setTokenNote(Constants.TOKEN_NOTE);
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        String configPath = this.config.getConfigPath();
        boolean configPathModified =  !settingsPanel.getConfigPath().equals(configPath);

        boolean targetConfigModified = !config.getTargetConfig().equals(this.getTargetConfig());
        return configPathModified || targetConfigModified;
    }

    @Override
    public void apply() {
        this.config.setConfigPath(this.settingsPanel.getConfigPath());
        this.config.setTargetConfig(this.getTargetConfig());
    }

    @Override
    public void reset() {
        settingsPanel.setSdkConfigValue(config.getConfigPath());
        settingsPanel.setUrlValue(config.getTargetConfig().getUrl());
        settingsPanel.setLoginValue(config.getTargetConfig().getUrl());
        settingsPanel.setTlsEnabledValue(config.getTargetConfig().getTlsEnabled());
        settingsPanel.setTokenValue(config.getTargetConfig().getToken());
        settingsPanel.setNamespaceValue(config.getTargetConfig().getNamespace());
    }

    public TargetConfig getTargetConfig(){
        return new TargetConfig(settingsPanel.getUrlValue(),
                                settingsPanel.getLoginValue(),
                                settingsPanel.getPasswordValue(),
                                settingsPanel.getTlsEnabledValue(),
                                settingsPanel.getTokenValue(),
                                settingsPanel.getNamespaceValue());
    }

}
