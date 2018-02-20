package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlTag;

import java.io.File;

public class AndroidManifest extends ConfigurationXml{

    private static final String MODULE_APP = "app";
    private static final String DIR_SRC = "src";
    private static final String DIR_MAIN = "main";
    private static final String ANDROID_MANIFEST_XML = "AndroidManifest.xml";
    private static final String ATTR_PACKAGE = "package";

    AndroidManifest() {}

    @Override
    public String getBundleId(Project project) {
        String packageName = null;
        String manifestPath = project.getBasePath() + File.separator + MODULE_APP + File.separator + DIR_SRC + File.separator + DIR_MAIN + File.separator + ANDROID_MANIFEST_XML;
        XmlTag manifest = this.getRootTag(project, manifestPath);
        if (manifest != null) {
            packageName = manifest.getAttributeValue(attrValue());
        }

        return packageName;
    }

    public String attrValue() {
        return ATTR_PACKAGE;
    }
}
