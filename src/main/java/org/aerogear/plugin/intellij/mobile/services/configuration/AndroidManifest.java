package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlTag;

import java.io.File;

public class AndroidManifest extends ConfigurationXml{

    public AndroidManifest() {}

    public String getPackage(Project project) {
        String packageName = "";
        String manifestPath = project.getBasePath() + File.separator + "app" + File.separator + "src" + File.separator + "main" + File.separator + "AndroidManifest.xml";
        XmlTag manifest = this.getRootTag(project, manifestPath);
        if (manifest != null) {
            packageName = manifest.getAttributeValue("package");
        }

        return packageName;
    }
}
