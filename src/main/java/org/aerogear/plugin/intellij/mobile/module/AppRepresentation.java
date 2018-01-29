package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.ide.util.projectWizard.EmptyModuleBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.SystemIndependent;
import org.picocontainer.PicoContainer;

import javax.swing.*;

public class AppRepresentation extends ModuleType {

  public AppRepresentation(@NotNull String id) {
    super(id);
  }

  @NotNull
  @Override
  public ModuleBuilder createModuleBuilder() {
    return new EmptyModuleBuilder();
  }

  @NotNull
  @Override
  public String getName() {
    return "mobile";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "app representqtion";
  }

  @Override
  public Icon getNodeIcon(boolean isOpened) {
    return null;
  }
}
