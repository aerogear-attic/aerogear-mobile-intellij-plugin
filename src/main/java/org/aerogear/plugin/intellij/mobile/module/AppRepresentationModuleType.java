package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.ide.util.projectWizard.EmptyModuleBuilder;
import org.aerogear.plugin.intellij.mobile.ui.MobileIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AppRepresentationModuleType extends ModuleType<AppRepresentationModuleBuilder> {

  public AppRepresentationModuleType() {
    super("mobile app representation module");
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
}
