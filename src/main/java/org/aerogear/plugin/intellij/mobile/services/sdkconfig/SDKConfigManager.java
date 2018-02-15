package org.aerogear.plugin.intellij.mobile.services.sdkconfig;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SDKConfigManager {
    public void createAndOpenFile(Project project, String path) throws CLIException, IOException {
        File configFile = new File(path);
        boolean fileCreated = configFile.createNewFile();
        if (fileCreated){
            Runnable updateConfigRunner = new WriteToFileRunnable(project, path, getClientConfig(project));
            Runnable openConfigRunner = new OpenFileRunnable(project, path);

            this.asyncRefreshFiles(() -> {
                WriteCommandAction.runWriteCommandAction(project, updateConfigRunner);
                ApplicationManager.getApplication().runReadAction(openConfigRunner);
                Objects.requireNonNull(AeroGearMobileConfiguration.getInstance(project)).setConfigPath(path);
            });
        } else {
            Objects.requireNonNull(AeroGearMobileConfiguration.getInstance(project)).setConfigPath(path);
        }
    }

    public void updateSDKConfig(Project project) throws CLIException {
        String sdkConfigPath = Objects.requireNonNull(AeroGearMobileConfiguration.getInstance(project)).getConfigPath();
        if (sdkConfigPath != null && !sdkConfigPath.isEmpty()) {
            this.asyncRefreshFiles(() -> {
                Runnable updateConfigRunner = new WriteToFileRunnable(project, sdkConfigPath, getClientConfig(project));
                WriteCommandAction.runWriteCommandAction(project, updateConfigRunner);
            });
        }
    }

    private String getClientName(Project project) {
        return Objects.requireNonNull(AeroGearMobileConfiguration.getInstance(project)).getClientName();
    }

    private void asyncRefreshFiles(Runnable r) {
        VirtualFileManager.getInstance().asyncRefresh(r);
    }

    private CharSequence getClientConfig(Project project) throws CLIException {
        String clientName = this.getClientName(project);
        return new MobileAPI(CLIRunnerImpl.getInstance()).getClientConfig(clientName);
    }

    public static SDKConfigManager getInstance(Project project) {
        return ServiceManager.getService(project, SDKConfigManager.class);
    }
}
