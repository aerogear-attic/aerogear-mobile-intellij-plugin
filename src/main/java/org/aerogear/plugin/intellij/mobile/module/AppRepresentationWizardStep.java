package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

public class AppRepresentationWizardStep extends ModuleWizardStep{
  private AppRepresentationModuleBuilder myBuilder;

  public AppRepresentationWizardStep(AppRepresentationModuleBuilder builder){
    myBuilder = builder;
  }
  @Override
  public JComponent getComponent() {
    return null;
  }

  @Override
  public void updateDataModel() {

  }
}
