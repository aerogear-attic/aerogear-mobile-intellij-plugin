package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

public class ClientCreatedCheckAction implements StartupActivity{
    @Override
    public void runActivity(@NotNull Project project) {
        System.out.println(">>>>>>");
    }
}
