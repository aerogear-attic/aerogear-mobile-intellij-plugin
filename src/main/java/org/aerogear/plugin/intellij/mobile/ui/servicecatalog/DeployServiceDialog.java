package org.aerogear.plugin.intellij.mobile.ui.servicecatalog;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import org.aerogear.plugin.intellij.mobile.api.CLIRunner;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.api.Watcher;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.ui.sdkconfig.ServiceDeployedNotification;
import org.aerogear.plugin.intellij.mobile.ui.servicecatalog.identity.IdentityDeployment;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class DeployServiceDialog extends DialogWrapper {
    private final Project project;
    private final ServiceClass sc;
    private final MobileAPI mobileAPI;
    private final MobileNotificationsService notifier = MobileNotificationsService.getInstance();

    private IdentityDeployment centerPanel;

    DeployServiceDialog(Project project, ServiceClass sc) {
        super(project);

        this.project = project;
        this.sc = sc;
        CLIRunner cliRunner = CLIRunnerImpl.getInstance();
        mobileAPI = new MobileAPI(cliRunner);

        init();
        setTitle(sc.getDisplayName());
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        centerPanel = new IdentityDeployment();
        centerPanel.setBackground(JBColor.WHITE);
        getContentPane().setBackground(JBColor.WHITE);
        getContentPanel().setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        return centerPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JComponent sp = super.createSouthPanel();
        sp.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new JBColor(new Color(232, 232, 231), JBColor.DARK_GRAY)));
        sp.setBackground(JBColor.WHITE);
        return sp;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();

        List<String> params = this.centerPanel.getConfig();
        Project project = this.project;
        ServiceClass sc = this.sc;
        ApplicationManager.getApplication().invokeLater(() -> mobileAPI.createService(sc, params, "myproject", new Watcher() {
            @Override
            public void onError(Exception e) {
                notifier.notifyError("", "Error while " + sc.getServiceName() + " deployed: " + e.toString());
            }

            @Override
            public void onSuccess(Object obj) {
                notifier.notify(new ServiceDeployedNotification(project, sc, obj));
            }
        }));
    }
}
