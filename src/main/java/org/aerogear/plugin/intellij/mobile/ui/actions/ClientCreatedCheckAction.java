package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.json.JsonFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.ui.createclientpopup.CreateClientForm;
import org.aerogear.plugin.intellij.mobile.utils.WriteToFileRunnable;

import java.util.Objects;

public class ClientCreatedCheckAction extends AnAction {
  private final MobileNotificationsService notificationsService;

  public ClientCreatedCheckAction() {
    this.notificationsService = new MobileNotificationsService();
  }
  
  @Override
  public void actionPerformed(AnActionEvent e) {
    MobileAPI mobileAPI = new MobileAPI(CLIRunnerImpl.getInstance());
    String filePath = Objects.requireNonNull(e.getProject()).getBasePath() + "/" + Constants.DOT_FILENAME;
    showCreateClientForm(e.getProject(), mobileAPI, filePath);
  }

  public void showCreateClientForm(Project project, MobileAPI mobileAPI, String filePath) {
    CreateClientForm clientForm = new CreateClientForm(project, mobileAPI);
    clientForm.show();

    if (CreateClientForm.OK_EXIT_CODE == clientForm.getExitCode()) {
      MobileClient mobileClient = mobileAPI.createClient(clientForm.getName(), clientForm.getClientType(), clientForm.getAppId());
      Objects.requireNonNull(AeroGearMobileConfiguration.getInstance(project)).setClientName(mobileClient.getSpec().getName() + "-" + mobileClient.getSpec().getClientType());

      CharSequence charSeq = mobileClient.getSpec().toJsonPrettyPrint();
      WriteToFileRunnable writeToFile = new WriteToFileRunnable(project, filePath, charSeq, Constants.DOT_FILENAME, JsonFileType.INSTANCE);
      WriteCommandAction.runWriteCommandAction(project, writeToFile);
      this.notificationsService.notifyInformation(Constants.MOBILE_CLIENT, "successfully created a mobile client ");
    }
  }
}
