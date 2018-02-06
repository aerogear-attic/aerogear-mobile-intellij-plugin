package org.aerogear.plugin.intellij.mobile.ui.actions;


import com.intellij.json.JsonFileType;
import com.intellij.notification.NotificationListener;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.ui.MobileIcons;
import org.aerogear.plugin.intellij.mobile.ui.createclientpopup.Constants;
import org.aerogear.plugin.intellij.mobile.ui.createclientpopup.CreateClientForm;
import org.aerogear.plugin.intellij.mobile.utils.WriteToFileRunnable;
import org.jetbrains.annotations.NotNull;


public class ClientCreatedCheck implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        String projectPath = project.getBasePath();
        checkFile(projectPath + '/' + Constants.DOT_FILENAME, project);
    }

    public void checkFile(String filePath, @NotNull Project project) {
        VirtualFile file = LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath);

        if (file == null) {
            MobileAPI mobileAPI = new MobileAPI(new CLIRunnerImpl());

            NotificationListener notificationListener = (notification, event) -> {
                {
                    new ClientCreatedCheckAction().showCreateClientForm(project, mobileAPI, filePath);
                }
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
