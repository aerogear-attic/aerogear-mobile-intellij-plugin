package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class AppRepresentationModuleBuilder extends ModuleBuilder implements ModuleBuilderListener{

  @Override
  public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
    //TODO
  }

  @Override
  public ModuleType getModuleType() {
    return AppRepresentationModuleType.getInstance();
  }


  @Nullable
  @Override
  public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
    return new AppRepresentationFirstStepWizard();
  }


  @Override
  public void moduleCreated(@NotNull Module module) {
    //TODO
  }


  @Override
  public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
    return new ModuleWizardStep[]{new AppRepresentationSecondStepWizard()};
  }
}
