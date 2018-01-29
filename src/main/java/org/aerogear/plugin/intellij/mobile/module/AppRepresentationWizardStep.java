package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

public class AppRepresentationWizardStep extends ModuleWizardStep{
  private final RsSdkPanel sdkPanel = new RsSdkPanel();
  private AppRepresentationModuleBuilder myBuilder;

  public AppRepresentationWizardStep(AppRepresentationModuleBuilder builder){
    myBuilder = builder;
  }
  @Override
  public JComponent getComponent() {
    return sdkPanel;
  }

  @Override
  public void updateDataModel() {

  }
}
