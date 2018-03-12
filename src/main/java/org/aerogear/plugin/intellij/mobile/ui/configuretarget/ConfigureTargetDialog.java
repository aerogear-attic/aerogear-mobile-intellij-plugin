package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.jetbrains.annotations.Nullable;
import javax.swing.JComponent;

public class ConfigureTargetDialog extends DialogWrapper {
    private AeroGearMobileConfiguration config;
    private ConfigureTargetPanel configureTargetPanel;

    public ConfigureTargetDialog(@Nullable Project project, AeroGearMobileConfiguration config) {
        super(project);
        this.config = config;
        init();
        setTitle(Constants.CONFIGURE_TARGET);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        configureTargetPanel = new ConfigureTargetPanel();
        configureTargetPanel.setUrlValue(config.getTargetConfig().getUrl());
        configureTargetPanel.setLoginValue(config.getTargetConfig().getLogin());
        configureTargetPanel.setTlsEnabledValue(config.getTargetConfig().getTlsEnabled());
        configureTargetPanel.setTokenValue(config.getTargetConfig().getToken());
        configureTargetPanel.setNamespaceValue(config.getTargetConfig().getNamespace());
        configureTargetPanel.setPasswordNote(Constants.PASSWORD_NOTE);
        configureTargetPanel.setTokenNote(Constants.TOKEN_NOTE);
        return configureTargetPanel;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
        config.setTargetConfig(this.getTargetConfig());
    }


    public TargetConfig getTargetConfig(){
        return new TargetConfig(configureTargetPanel.getUrlValue(),
                                configureTargetPanel.getLoginValue(),
                                configureTargetPanel.getPasswordValue(),
                                configureTargetPanel.getTlsEnabledValue(),
                                configureTargetPanel.getTokenValue(),
                                configureTargetPanel.getNamespaceValue());
    }
}
