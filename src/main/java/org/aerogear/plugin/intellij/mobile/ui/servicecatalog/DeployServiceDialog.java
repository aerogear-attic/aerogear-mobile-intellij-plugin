package org.aerogear.plugin.intellij.mobile.ui.servicecatalog;

import com.intellij.notification.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogWrapper;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.api.Watch;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.ui.servicecatalog.identity.IdentityDeployment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DeployServiceDialog extends DialogWrapper {
    private ServiceClass sc;
    private IdentityDeployment centerPanel;
    private MobileAPI mobileAPI = new MobileAPI();

    protected DeployServiceDialog(ServiceClass sc) {
        super(null);
        this.sc = sc;
        init();
        setTitle(sc.getDisplayName());
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[]{new DeployOnOkAction(this), getCancelAction()};
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        centerPanel = new IdentityDeployment();
        centerPanel.setBackground(Color.WHITE);
        getContentPane().setBackground(Color.WHITE);
        getContentPanel().setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        return centerPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JComponent sp = super.createSouthPanel();
        sp.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(232, 232, 231)));
        sp.setBackground(Color.WHITE);
        return sp;
    }

    public ServiceClass getServiceClass() {
        return sc;
    }

    private class DeployOnOkAction extends OkAction {
        private DeployServiceDialog sd;
        private MobileNotificationsService notifier;

        public DeployOnOkAction(DeployServiceDialog sd) {
            this.sd = sd;
            notifier = ServiceManager.getService(MobileNotificationsService.class);
        }

        @Override
        protected void doAction(ActionEvent e) {
            super.doAction(null);
            List<String> params = this.sd.centerPanel.getConfig();
            mobileAPI.createService(sd.getServiceClass(), params, new Watch() {
                @Override
                public void onError(Exception e) {
                    notifier.notifyError("", "Error while " + sc.getServiceName() + " deployed: " + e.toString());
                }

                @Override
                public void onSuccess(Object obj) {
                    notifier.notifyInformation("", sc.getServiceName() + " deployment complete.");
                }
            });
        }
    }
}
