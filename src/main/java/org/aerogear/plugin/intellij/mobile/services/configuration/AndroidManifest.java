package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlTag;

import java.io.File;

public class AndroidManifest extends ConfigurationXml{

    public AndroidManifest() {}

    public String getPackage(Project project) {
        String packageName = "";
        XmlTag manifest = this.getRootTag(project,project.getBasePath() + File.separator + "AndroidManifest.xml");
        if (manifest != null) {
            packageName = manifest.getAttributeValue("package");
        }

        return packageName;
    }
}
