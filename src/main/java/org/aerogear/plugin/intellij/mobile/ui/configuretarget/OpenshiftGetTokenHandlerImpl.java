package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunner;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;


public class OpenshiftGetTokenHandlerImpl {
    private final CLIRunner cliRunner = CLIRunnerImpl.getInstance();
    private final MobileAPI mobileAPI = new MobileAPI(cliRunner);
    private final MobileNotificationsService mobileNotificationsService = MobileNotificationsService.getInstance();

    public OpenshiftGetTokenHandlerImpl() {}

    public String handle(OpenshiftGetTokenHandler handler) {
        String token = null;
        try {
            mobileAPI.ocLogin(handler.getUrlValue().getText(),
                              handler.getLoginValue().getText(),
                              handler.getPasswordValue().getText(),
                              handler.getTlsEnabledValue().isSelected());
            token = mobileAPI.getOpenshiftToken();
        } catch (CLIException e) {
            mobileNotificationsService.notifyError(e.getMessage());
        }

        return token;
    }
}
