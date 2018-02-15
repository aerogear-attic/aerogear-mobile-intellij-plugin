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
    @Override
    public void actionPerformed(AnActionEvent e) {
        try {
            String clientName = Objects.requireNonNull(AeroGearMobileConfiguration.getInstance(e.getProject())).getClientName();
            if (null == clientName){
                throw new CLIException("cannot get config as no mobile client created");
            }
            String clientSdkConfig = new MobileAPI(CLIRunnerImpl.getInstance()).getClientConfig(clientName);
            new ViewSDKConfigDialog(clientSdkConfig).show();
        } catch (CLIException ex) {
            MobileNotificationsService.getInstance().notifyError("Error from mobile plugin: " + ex.toString());
        }
    }
}
