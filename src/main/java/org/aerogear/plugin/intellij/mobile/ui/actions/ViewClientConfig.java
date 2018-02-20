package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.ui.sdkconfig.ViewSDKConfigDialog;

import java.util.Objects;

public class ViewClientConfig extends AnAction {

    private MobileNotificationsService mobileNotificationsService = MobileNotificationsService.getInstance();

    @Override
    public void actionPerformed(AnActionEvent e) {
        try {
            String clientName = Objects.requireNonNull(AeroGearMobileConfiguration.getInstance(e.getProject())).getClientName();
            if (clientName != null) {
                String clientSdkConfig = new MobileAPI(CLIRunnerImpl.getInstance()).getClientConfig(clientName, "myproject");
                new ViewSDKConfigDialog(clientSdkConfig).show();
            } else {
                mobileNotificationsService.notifyError("Error from mobile plugin", "Cannot get config: no mobile client exists");
            }
        } catch (CLIException ex) {
            mobileNotificationsService.notifyError("Error from mobile plugin", ex.toString());
        }
    }
}
