package org.aerogear.plugin.intellij.mobile.ui.servicecatalog.identity;

import org.aerogear.plugin.intellij.mobile.ui.servicecatalog.Deployable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class IdentityDeployment extends JPanel implements Deployable {
    private final IdentityConfigurationPanel configPanel;

    public IdentityDeployment() {
        this.configPanel = new IdentityConfigurationPanel();
        this.add(this.configPanel);
    }

    @Override
    public List<String> getConfig() {
        List<String> params = new ArrayList<>();

        params.add("--namespace=" + this.configPanel.getProjectSelector().getSelectedItem());
        params.add("-p ADMIN_NAME=" + this.configPanel.getUsernameValue().getText());
        params.add("-p ADMIN_PASSWORD=" + this.configPanel.getPasswordValue().getText());
        return params;
    }
}
