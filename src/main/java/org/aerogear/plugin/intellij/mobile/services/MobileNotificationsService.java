package org.aerogear.plugin.intellij.mobile.services;

import com.intellij.notification.*;

public class MobileNotificationsService {

    private static final String AEROGEAR_NOTIFICATION_GROUP = "Aerogear Mobile Notifications";

    public MobileNotificationsService() {}

    public void notifyInformation(String title, String content) {
        Notifications.Bus.notify(new Notification(AEROGEAR_NOTIFICATION_GROUP, title, content, NotificationType.INFORMATION));
    }

    public void notifyError(String title, String content) {
        Notifications.Bus.notify(new Notification(AEROGEAR_NOTIFICATION_GROUP, title, content, NotificationType.ERROR));
    }
}
