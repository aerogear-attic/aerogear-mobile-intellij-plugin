package org.aerogear.plugin.intellij.mobile.services;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ServiceManager;

import javax.swing.*;

public class MobileNotificationsService {

    public static final String AEROGEAR_NOTIFICATION_GROUP = "Aerogear Mobile Notifications";

    private MobileNotificationsService() {}

    public void notifyInformation(String title, String content) {
        Notifications.Bus.notify(new Notification(AEROGEAR_NOTIFICATION_GROUP, title, content, NotificationType.INFORMATION));
    }

    public void notifyInformation(String content, NotificationListener nl) {
        Notifications.Bus.notify(new Notification(AEROGEAR_NOTIFICATION_GROUP, "", content, NotificationType.INFORMATION, nl));
    }

    public void notify(Notification n) {
        Notifications.Bus.notify(n);
    }

    public void notifyError(String title, String content) {
        Notifications.Bus.notify(new Notification(AEROGEAR_NOTIFICATION_GROUP, title, content, NotificationType.ERROR));
    }

    public void notifyError(String content) {
        Notifications.Bus.notify(new Notification(AEROGEAR_NOTIFICATION_GROUP, "", content, NotificationType.ERROR));
    }
    
    public void notifyWarning(Icon icon, String title, String content, NotificationListener notificationListener) {
        Notifications.Bus.notify(new Notification(
                AEROGEAR_NOTIFICATION_GROUP,
                icon,
                title,
                "",
                content,
                NotificationType.WARNING,
                notificationListener
        ));
    }

    public void notifyWarning(String content, NotificationListener nl) {
        Notifications.Bus.notify(new Notification(AEROGEAR_NOTIFICATION_GROUP, "", content, NotificationType.WARNING, nl));
    }

    public static MobileNotificationsService getInstance() {
        return ServiceManager.getService(MobileNotificationsService.class);
    }
}
