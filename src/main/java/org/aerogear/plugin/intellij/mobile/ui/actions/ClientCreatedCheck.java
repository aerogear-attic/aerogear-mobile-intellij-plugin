package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.notification.NotificationListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.services.configuration.ProjectConfiguration;
import org.aerogear.plugin.intellij.mobile.ui.MobileIcons;
import org.jetbrains.annotations.NotNull;

public class ClientCreatedCheck implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        String projectPath = project.getBasePath();
        checkFile(projectPath + '/' + Constants.DOT_FILENAME, project);
    }

    private void checkFile(String filePath, @NotNull Project project) {
        VirtualFile file = ApplicationManager.getApplication().runReadAction(new Computable<VirtualFile>() {
            @Override
            public VirtualFile compute() {
                return LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath);
            }
        });

        if (file == null) {
            MobileAPI mobileAPI = new MobileAPI(CLIRunnerImpl.getInstance());

            NotificationListener notificationListener = (notification, event) -> {
                String appId = ProjectConfiguration.getInstance(project).getAppId(project);
                new ClientCreatedCheckAction().showCreateClientForm(project, mobileAPI, filePath, appId);
            };

            new MobileNotificationsService().notifyWarning(
                    MobileIcons.AEROGEAR,
                    "<html>Client app definition not found",
                    " <a href=\"" + "here" + "\" target=\"blank\">Create new client app definition</a> </html>",
                    notificationListener
            );
        }
    }

}
