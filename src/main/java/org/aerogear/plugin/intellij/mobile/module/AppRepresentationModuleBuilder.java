package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class AppRepresentationModuleBuilder extends ModuleBuilder implements ModuleBuilderListener{

  @Override
  public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
    //TODO
  }

  @Override
  public ModuleType getModuleType() {
    return new AppRepresentationModuleType();
  }

  @Override
  public ModuleWizardStep[] createWizardSteps(WizardContext wizardContext, ModulesProvider modulesProvider) {
    return new ModuleWizardStep[]{new AppRepresentationWizardStep(this)};
  }

  @Override
  public void moduleCreated(@NotNull Module module) {
    //TODO
  }
}
