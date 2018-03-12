package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunner;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;

import javax.swing.JComponent;

public class OpenshiftGetTokenHandlerImpl {
    private final CLIRunner cliRunner = CLIRunnerImpl.getInstance();
    private final MobileAPI mobileAPI = new MobileAPI(cliRunner);
    private final MobileNotificationsService mobileNotificationsService = MobileNotificationsService.getInstance();

    public OpenshiftGetTokenHandlerImpl() { }

    public String handle(String url,
                         String login,
                         String password,
                         boolean tlsEnabled) {
        String token = null;
        try {
            mobileAPI.ocLogin(url, login, password, tlsEnabled);
            token = mobileAPI.getOpenshiftToken();
        } catch (CLIException e) {
            mobileNotificationsService.notifyError(e.getMessage());
        }

        return token;
    }
}
