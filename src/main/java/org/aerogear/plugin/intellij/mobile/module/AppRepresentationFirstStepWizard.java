package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;
import java.util.Map;

public class AppRepresentationFirstStepWizard extends ModuleWizardStep {
  private CreateClientForm clientForm;

  public AppRepresentationFirstStepWizard() {
    clientForm = new CreateClientForm();
  }


  @Override
  public JComponent getComponent() {
    return clientForm;
  }

  @Override
  public void updateDataModel() {
    //TODO this executes on "next"
    Map map = clientForm.getInputs();


    //TODO do not proceed to the next step if name is missing
    if (map.get("name").equals("")) {
      clientForm.invalidNameNotify();
    } else {
      clientForm.validNameNotify();
    }

  }
}
