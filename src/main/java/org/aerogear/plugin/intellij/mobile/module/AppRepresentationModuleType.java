package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.ide.util.projectWizard.EmptyModuleBuilder;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.aerogear.plugin.intellij.mobile.ui.MobileIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AppRepresentationModuleType extends ModuleType<AppRepresentationModuleBuilder> {
  private static final String ID = "MOBILE_MODULE";

  public AppRepresentationModuleType() {
    super(ID);
  }

  public static AppRepresentationModuleType getInstance() {
    return (AppRepresentationModuleType) ModuleTypeManager.getInstance().findByID(ID);
  }

  @NotNull
  @Override
  public AppRepresentationModuleBuilder createModuleBuilder() {
    return new AppRepresentationModuleBuilder();
  }


  @NotNull
  @Override
  public String getName() {
    return "Mobile app";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Mobile app representation";
  }

  @Override
  public Icon getNodeIcon(boolean isOpened) {
    return MobileIcons.Feedhenry;
  }

//  @NotNull
//  @Override
//  public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull AppRepresentationModuleBuilder moduleBuilder, @NotNull ModulesProvider modulesProvider) {
//    return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider);
//  }


}
