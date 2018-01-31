package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import org.aerogear.plugin.intellij.mobile.api.CliRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;

import javax.swing.JComponent;
import java.util.Map;

public class AppRepresentationFirstStepWizard extends ModuleWizardStep {
  private CreateClientForm clientForm;
  private Map inputsMap;
  private MobileAPI mobileAPI;


  public AppRepresentationFirstStepWizard() {
    clientForm = new CreateClientForm();
    mobileAPI  = new MobileAPI(new CliRunnerImpl());
  }


  @Override
  public JComponent getComponent() {
    return clientForm;
  }

  @Override
  public void updateDataModel() {
    mobileAPI.createApprepresentation(inputsMap);
  }

  public boolean validate() {
    inputsMap = clientForm.getInputs();

    if (inputsMap.get("name").equals("")) {
      clientForm.invalidNameNotify();
      return false;
    } else {
      clientForm.validNameNotify();
      return true;
    }
  }

}
