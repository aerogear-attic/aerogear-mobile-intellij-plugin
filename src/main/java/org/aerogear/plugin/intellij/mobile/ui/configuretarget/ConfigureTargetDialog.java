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
        configureTargetPanel.getUrlValue().setText(config.getTargetConfig().getUrl());
        configureTargetPanel.getLoginValue().setText(config.getTargetConfig().getLogin());
        configureTargetPanel.getTlsEnabledValue().setSelected(config.getTargetConfig().getTlsEnabled());
        configureTargetPanel.getTokenValue().setText(config.getTargetConfig().getToken());
        configureTargetPanel.getNamespaceValue().setText(config.getTargetConfig().getNamespace());

        configureTargetPanel.getPasswordNote().setText(Constants.PASSWORD_NOTE);
        configureTargetPanel.getTokenNote().setText(Constants.TOKEN_NOTE);

        configureTargetPanel.getGetTokenBtn().addActionListener(e ->
                configureTargetPanel.getTokenValue().setText(new OpenshiftGetTokenHandlerImpl().handle(configureTargetPanel)));

        return configureTargetPanel;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
        config.setTargetConfig(this.getTargetConfig());
    }

    public TargetConfig getTargetConfig(){
        return new TargetConfig(configureTargetPanel.getUrlValue().getText(),
                                configureTargetPanel.getLoginValue().getText(),
                                configureTargetPanel.getPasswordValue().getText(),
                                configureTargetPanel.getTlsEnabledValue().isSelected(),
                                configureTargetPanel.getTokenValue().getText(),
                                configureTargetPanel.getNamespaceValue().getText());
    }
}
