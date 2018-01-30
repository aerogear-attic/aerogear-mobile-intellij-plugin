package org.aerogear.plugin.intellij.mobile.services.sdkconfig;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunner;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;

import java.io.File;
import java.io.IOException;

public class SDKConfigManager {
    public void createAndOpenFile(Project project, String path) {
        File configFile = new File(path);
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runnable updateConfigRunner = new WriteToFileRunnable(project, path, getClientConfig());
        Runnable openConfigRunner = new OpenFileRunnable(project, path);

        this.asyncRefreshFiles(new Runnable() {
            @Override
            public void run() {
                WriteCommandAction.runWriteCommandAction(project, updateConfigRunner);
                ApplicationManager.getApplication().runReadAction(openConfigRunner);
                AeroGearMobileConfiguration.getInstance(project).setConfigPath(path);
            }
        });
    }

    public void updateSDKConfig(Project project) {
        String sdkConfigPath = AeroGearMobileConfiguration.getInstance(project).getConfigPath();
        if (sdkConfigPath != null && !sdkConfigPath.isEmpty()) {
            this.asyncRefreshFiles(new Runnable() {
                @Override
                public void run() {
                    Runnable updateConfigRunner = new WriteToFileRunnable(project, sdkConfigPath, getClientConfig());
                    WriteCommandAction.runWriteCommandAction(project, updateConfigRunner);
                }
            });
        }
    }

    private void asyncRefreshFiles(Runnable r) {
        VirtualFileManager.getInstance().asyncRefresh(r);
    }

    private CharSequence getClientConfig() {
        CharSequence cs = "";
        try {
            cs = new MobileAPI(new CLIRunnerImpl()).getClientConfig("myapp-cordova");
        } catch (CLIException ex) {
            MobileNotificationsService.getInstance().notifyError("Error from mobile plugin: " + ex.toString());
        }
        return cs;
    }

    public static SDKConfigManager getInstance(Project project) {
        return ServiceManager.getService(project, SDKConfigManager.class);
    }
}
