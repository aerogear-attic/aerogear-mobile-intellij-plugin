package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.aerogear.plugin.intellij.mobile.services.AeroGearMobileConfiguration;
import org.aerogear.plugin.intellij.mobile.ui.configuretarget.ConfigureTargetDialog;

import java.util.Objects;

public class ConfigureTargetAction extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    Project project = Objects.requireNonNull(e.getProject());
      new ConfigureTargetDialog(project, AeroGearMobileConfiguration.getInstance(project)).show();
  }
}
