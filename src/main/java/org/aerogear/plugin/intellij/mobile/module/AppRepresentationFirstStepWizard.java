package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.options.ConfigurationException;

import javax.swing.*;
import java.util.Map;

public class AppRepresentationFirstStepWizard extends ModuleWizardStep {
  private CreateClientForm clientForm;
  private Map inputsMap;


  public AppRepresentationFirstStepWizard() {
    clientForm = new CreateClientForm();
  }


  @Override
  public JComponent getComponent() {
    return clientForm;
  }

  @Override
  public void updateDataModel() {
  }

  public boolean validate() throws ConfigurationException {
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
