package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

public class AppRepresentationSecondStepWizard extends ModuleWizardStep{

  public AppRepresentationSecondStepWizard(){
  }


  @Override
  public JComponent getComponent() {
    return new JLabel("Put your content here");
  }

  @Override
  public void updateDataModel() {

  }
}
