package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.aerogear.plugin.intellij.mobile.ui.MobileIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;


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
    return MobileIcons.FEEDHENRY;
  }

}
