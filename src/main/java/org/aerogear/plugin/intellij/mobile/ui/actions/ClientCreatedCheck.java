package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.google.gson.Gson;
import com.intellij.notification.NotificationListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunner;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.services.configuration.ProjectConfiguration;
import org.aerogear.plugin.intellij.mobile.ui.MobileIcons;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class ClientCreatedCheck implements StartupActivity {

    private CLIRunner cliRunner = CLIRunnerImpl.getInstance();

    @Override
    public void runActivity(@NotNull Project project) {
        String projectPath = project.getBasePath();
        checkFile(projectPath + '/' + Constants.DOT_FILENAME, project);
    }

    private void checkFile(String filePath, @NotNull Project project) {

        MobileAPI mobileAPI = new MobileAPI(cliRunner);
        VirtualFile file = ApplicationManager.getApplication().runReadAction((Computable<VirtualFile>) () -> {
            return LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath);
        });

        // If a client representation does not exist, prompt the user to create one
        if (file == null) {
            String title = "Client app definition not found";
            createClientNotification(project, mobileAPI, filePath, title);
        }
        // If there is a client representation, check to see that the client still exists
        else {
            Gson gson = new Gson();

            try {
                // Get the client config as properties
                String configFile = new String(file.contentsToByteArray());
                Properties clientConfig = gson.fromJson(configFile, Properties.class);
                String clientId = clientConfig.getProperty("name") + '-' + clientConfig.getProperty("clientType");

                if (!clientExists(clientId)) {
                    String title = "Client " + clientId + " no longer exists in current namespace";
                    createClientNotification(project, mobileAPI, filePath, title);
                }
            } catch (IOException e) {
                throw new CLIException("Unable to read client configuration");
            }
        }
    }

    /**
     * Reusable balloon notification which prompts a user to create
     * a new client representation
     */
    private void createClientNotification(@NotNull Project project, MobileAPI mobileAPI, String filePath, String title) {
        NotificationListener notificationListener = (notification, event) -> {
            String appId = ProjectConfiguration.getInstance(project).getAppId(project);
            new ClientCreatedCheckAction().showCreateClientForm(project, mobileAPI, filePath, appId);
        };

        new MobileNotificationsService().notifyWarning(
                MobileIcons.AEROGEAR,
                "<html>" + title,
                " <a href=\"" + "here" + "\" target=\"blank\">Create a new client app definition</a> </html>",
                notificationListener
        );
    }

    /**
     * Utility method to check whether the client exists on the connected
     * cluster
     */
    private Boolean clientExists(String clientId) {
        try {
            cliRunner.executeSync(Arrays.asList("get", "client", clientId, "--", "-o=json"));
        } catch (CLIException e) {
            return false;
        }

        return true;
    }

}
