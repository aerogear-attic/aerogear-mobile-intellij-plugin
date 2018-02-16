package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;

public class ProjectConfiguration {

    public ProjectConfiguration() {}

    public String getAppId(Project project) {
        String appId = new AndroidManifest().getPackage(project);
        if (appId.isEmpty()) {
            appId = new CordovaConfig().getId(project);
        }

        return appId;
    }

    public static ProjectConfiguration getInstance(Project project) {
        return ServiceManager.getService(project, ProjectConfiguration.class);
    }
}
