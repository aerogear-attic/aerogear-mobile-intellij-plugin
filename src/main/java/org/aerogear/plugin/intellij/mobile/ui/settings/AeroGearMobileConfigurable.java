package org.aerogear.plugin.intellij.mobile.ui.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.aerogear.plugin.intellij.mobile.ui.configuretarget.Constants;
import org.aerogear.plugin.intellij.mobile.ui.configuretarget.OpenshiftGetTokenHandlerImpl;
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
        settingsPanel = new SettingsPanel();
        settingsPanel.getPasswordNote().setText(Constants.PASSWORD_NOTE);
        settingsPanel.getTokenNote().setText(Constants.TOKEN_NOTE);
        settingsPanel.getGetTokenBtn().addActionListener(e ->
                settingsPanel.getTokenValue().setText(new OpenshiftGetTokenHandlerImpl().handle(settingsPanel)));

        settingsPanel.getSdkConfigValue().addBrowseFolderListener(
                new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFileDescriptor(), project)
        );
        GroupLayout sdkConfigLayout = (GroupLayout)  settingsPanel.getSdkConfigPanel().getLayout();
        sdkConfigLayout.replace(settingsPanel.getPlaceholderTextField(), settingsPanel.getSdkConfigValue());
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        String configPath = this.config.getConfigPath();
        boolean configPathModified =  !settingsPanel.getSdkConfigValue().getText().equals(configPath);

        boolean targetConfigModified = !config.getTargetConfig().equals(this.getTargetConfig());
        return configPathModified || targetConfigModified;
    }

    @Override
    public void apply() {
        this.config.setConfigPath(this.settingsPanel.getSdkConfigValue().getText());
        this.config.setTargetConfig(this.getTargetConfig());
    }

    @Override
    public void reset() {
        settingsPanel.getSdkConfigValue().setText(config.getConfigPath());
        settingsPanel.getUrlValue().setText(config.getTargetConfig().getUrl());
        settingsPanel.getLoginValue().setText(config.getTargetConfig().getLogin());
        settingsPanel.getTlsEnabledValue().setSelected(config.getTargetConfig().getTlsEnabled());
        settingsPanel.getTokenValue().setText(config.getTargetConfig().getToken());
        settingsPanel.getNamespaceValue().setText(config.getTargetConfig().getNamespace());
    }

    public TargetConfig getTargetConfig(){
        return new TargetConfig(settingsPanel.getUrlValue().getText(),
                                settingsPanel.getLoginValue().getText(),
                                settingsPanel.getPasswordValue().getText(),
                                settingsPanel.getTlsEnabledValue().isSelected(),
                                settingsPanel.getTokenValue().getText(),
                                settingsPanel.getNamespaceValue().getText());
    }

}
