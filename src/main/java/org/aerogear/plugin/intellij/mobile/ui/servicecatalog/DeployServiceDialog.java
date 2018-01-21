package org.aerogear.plugin.intellij.mobile.ui.servicecatalog;

import com.intellij.openapi.ui.DialogWrapper;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;
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

    protected DeployServiceDialog(ServiceClass sc) {
        super(null);
        init();
        this.sc = sc;
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

        public DeployOnOkAction(DeployServiceDialog sd) {
            this.sd = sd;
        }

        @Override
        protected void doAction(ActionEvent e) {
            super.doAction(null);
            List<String> params = this.sd.centerPanel.getConfig();
            new MobileAPI().createService(sd.getServiceClass(), params);
        }
    }
}
