package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlTag;

import java.io.File;
import java.util.ArrayList;

public class CordovaConfig extends ConfigurationXml {

    private static final String ATTR_ID = "id";
    private static final String MODULE_APP = "app";
    private static final String CONFIG_XML = "config.xml";
    private static final String DIR_WWW = "www";

    CordovaConfig() {}

    @Override
    public String getBundleId(Project project) {
        ArrayList<String> possiblePaths = new ArrayList<>();
        String basePath = project.getBasePath();
        possiblePaths.add(basePath + File.separator + CONFIG_XML);
        possiblePaths.add(basePath + File.separator + MODULE_APP + File.separator + CONFIG_XML);
        possiblePaths.add(basePath + File.separator + MODULE_APP + File.separator + DIR_WWW + File.separator + CONFIG_XML);

        String id = null;
        for(int i = 0; i < possiblePaths.size() && id == null; i++) {
            XmlTag widget = this.getRootTag(project, possiblePaths.get(i));
            if (widget != null) {
                id = widget.getAttributeValue(attrValue());
            }
        }

        return id;
    }

    @Override
    public String attrValue() {
        return ATTR_ID;
    }
}
