package org.aerogear.plugin.intellij.mobile.ui.sdkconfig;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.services.sdkconfig.SDKConfigManager;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

class CreateConfigDialog extends DialogWrapper {

    private final Project project;
    private TextFieldWithBrowseButton destination;
    private JTextField fileName;

    CreateConfigDialog(@Nullable Project project) {
        super(project);
        this.project = project;

        init();
        setTitle(Constants.SDK_CONFIG);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        NewFileForm nff = new NewFileForm();
        this.destination = nff.getDestinationInput();
        this.destination.addBrowseFolderListener(new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFolderDescriptor(), this.project));
        this.fileName = nff.getFileNameInput();

        return nff;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
        String fullPath = this.destination.getText() + File.separator + this.fileName.getText();
        try {
            SDKConfigManager.getInstance(this.project).createAndOpenFile(this.project, fullPath);
        } catch (Exception e) {
            if (e instanceof CLIException) {
                MobileNotificationsService.getInstance().notifyError("Error from mobile plugin: " + e.toString());
            }
            if (e instanceof IOException) {
                MobileNotificationsService.getInstance().notifyError("Error creating SDK Config file: " + e.toString());
            }
        }
    }
}
