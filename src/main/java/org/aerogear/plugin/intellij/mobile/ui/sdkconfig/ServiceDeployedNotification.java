package org.aerogear.plugin.intellij.mobile.ui.sdkconfig;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.services.sdkconfig.SDKConfigManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkEvent;

public class ServiceDeployedNotification extends Notification {
    private Project project;
    private ServiceClass sc;

    public ServiceDeployedNotification(Project project, ServiceClass sc) {
        super(MobileNotificationsService.AEROGEAR_NOTIFICATION_GROUP,sc.getDisplayName() + " deployment complete", "", NotificationType.INFORMATION);

        this.project = project;
        this.sc = sc;


        String content = this.createContent();
        this.setContent(content);
        this.setListener(this.getNotificationListener());
    }

    private String createContent() {
        String sdkConfigPath = AeroGearMobileConfiguration.getInstance(this.project).getConfigPath();
        String content = "";
        if (sdkConfigPath == null || sdkConfigPath.isEmpty()) {
            content += "<p>SDK config settings are not set.</p>";
            content += "<p>If you haven't already, <a href=\"create-config\">add sdk config file</a></p>";
            content += "<p>or</p>";
            content += "<p>if SDK config file exists <a href=\"open-settings\">set SDK config path setting</a></p>";
            content += "<p>then <a href=\"update-config\">Update SDK config</a></p>";
        } else {
            content += "<a href=\"update-config\">Update SDK config</a>";
        }

        return content;
    }

    private NotificationListener getNotificationListener() {
        Project project = this.project;

        return new NotificationListener() {
            @Override
            public void hyperlinkUpdate(@NotNull Notification notification, @NotNull HyperlinkEvent event) {
                if (event.getDescription().equals("create-config")) {
                    new CreateConfigDialog(project).show();
                } else if (event.getDescription().equals("open-settings")) {
                    ShowSettingsUtil.getInstance().showSettingsDialog(project, "AeroGear Mobile");
                } else if (event.getDescription().equals("update-config")) {
                    try {
                        SDKConfigManager.getInstance(project).updateSDKConfig(project);
                    } catch(CLIException ex) {
                        MobileNotificationsService.getInstance().notifyError("Error from mobile plugin: " + ex.toString());
                    }
                }
            }
        };
    }
}