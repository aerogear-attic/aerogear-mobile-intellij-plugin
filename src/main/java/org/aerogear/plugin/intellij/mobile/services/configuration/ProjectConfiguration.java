package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;


public class ProjectConfiguration {

    private ProjectConfiguration() {}

    public String getAppId(Project project) {
        String appId = new AndroidManifest().getBundleId(project);
        if (appId == null) {
            appId = new CordovaConfig().getBundleId(project);
        }

        if (appId == null) throw new NullAppIdException(
                "Unable to find Application ID, AndroidManifest.xml and Cordova config.xml appear to be missing");
        return appId;
    }

    public static ProjectConfiguration getInstance(Project project) {
        return ServiceManager.getService(project, ProjectConfiguration.class);
    }
}
