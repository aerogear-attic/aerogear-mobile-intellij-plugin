package org.aerogear.plugin.intellij.mobile.ui.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class AeroGearMobileConfigurable implements Configurable {

    private final AeroGearMobileConfiguration config;
    private final Project project;
    private TextFieldWithBrowseButton configPathValue;

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
        SettingsPanel settingsPanel = new SettingsPanel();
        this.configPathValue = settingsPanel.getSdkConfigValue();
        this.configPathValue.addBrowseFolderListener(new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFileDescriptor(), project));
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        String configPath = this.config.getConfigPath();
        return configPath != null && !configPath.equals(this.configPathValue.getText());
    }

    @Override
    public void apply() {
        this.config.setConfigPath(this.configPathValue.getText());
    }

    @Override
    public void reset() {
        String configPath = this.config.getConfigPath();
        if (configPath != null) {
            this.configPathValue.setText(configPath);
        }
    }
}
