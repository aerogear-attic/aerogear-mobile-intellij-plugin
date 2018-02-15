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

import java.util.Objects;

public class ServiceDeployedNotification extends Notification {
    private final Project project;
    private final Object deployOutput;

    public ServiceDeployedNotification(Project project, ServiceClass sc, Object obj) {
        super(MobileNotificationsService.AEROGEAR_NOTIFICATION_GROUP,sc.getDisplayName() + " deployment complete", "deployment", NotificationType.INFORMATION);

        this.project = project;
        this.deployOutput = obj;


        String content = this.createContent();
        this.setContent(content);
        this.setListener(this.getNotificationListener());
    }

    private String createContent() {
        String sdkConfigPath = Objects.requireNonNull(AeroGearMobileConfiguration.getInstance(this.project)).getConfigPath();
        StringBuilder content = new StringBuilder(); // TODO issues/19
        if (sdkConfigPath == null || sdkConfigPath.isEmpty()) {
            content.append("<p>SDK config settings are not set.</p>");
            content.append("<p>If you haven't already, <a href=\"create-config\">add sdk config file</a></p>");
            content.append("<p>or</p>") ;
            content.append("<p>if SDK config file exists <a href=\"open-settings\">set SDK config path setting</a></p>");
            content.append("<p>then <a href=\"update-config\">Update SDK config</a></p>");
        } else {
            content.append("<a href=\"update-config\">Update SDK config</a>");
        }

        return content.toString();
    }

    private NotificationListener getNotificationListener() {
        Project project = this.project;

        return (notification, event) -> {
            switch (event.getDescription()) {
                case Constants.CREATE_CONFIG:
                    new CreateConfigDialog(project).show();
                    break;
                case Constants.OPEN_SETTINGS:
                    ShowSettingsUtil.getInstance().showSettingsDialog(project, "AeroGear Mobile");
                    break;
                case Constants.UPDATE_CONFIG:
                    try {
                        SDKConfigManager.getInstance(project).updateSDKConfig(project);
                    } catch (CLIException ex) {
                        MobileNotificationsService.getInstance().notifyError("Error from mobile plugin: " + ex.toString());
                    }
                    break;
                 default:
                     throw new IllegalArgumentException("no other settings are supported atm.");
            }
        };
    }

    public Object getDeployOutput() {
        return deployOutput;
    }
}
