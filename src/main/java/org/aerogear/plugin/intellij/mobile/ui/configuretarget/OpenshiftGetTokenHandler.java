package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunner;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;

public class OpenshiftGetTokenHandler {
    private final CLIRunner cliRunner = CLIRunnerImpl.getInstance();
    private final MobileAPI mobileAPI = new MobileAPI(cliRunner);
    private final MobileNotificationsService mobileNotificationsService = MobileNotificationsService.getInstance();

    private final TargetConfig config;


    public OpenshiftGetTokenHandler(TargetConfig targetConfig) {
        this.config = targetConfig;
    }

    public String handle() {
        String token = "";
        try {
            mobileAPI.ocLogin(config.getUrl(),
                              config.getLogin(),
                              config.getPassword());
            token = mobileAPI.getOpenshiftToken();
        } catch (CLIException e) {
            mobileNotificationsService.notifyError(e.getMessage());
        }

        return token;
    }
}
