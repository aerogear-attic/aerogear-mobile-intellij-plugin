package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

public class AppRepresentationFirstStepWizard extends ModuleWizardStep{

  public AppRepresentationFirstStepWizard(){
  }


  @Override
  public JComponent getComponent() {
    return new JLabel("custom step from builder Provide some setting here");
  }

  @Override
  public void updateDataModel() {

  }
}
