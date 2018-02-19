package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlTag;

import java.io.File;
import java.util.ArrayList;

public class CordovaConfig extends ConfigurationXml {

    public CordovaConfig() {}

    public String getId(Project project) {
        ArrayList<String> possiblePaths = new ArrayList<>();
        String basePath = project.getBasePath();
        possiblePaths.add(basePath + File.separator + "config.xml");
        possiblePaths.add(basePath + File.separator + "app" + File.separator + "config.xml");
        possiblePaths.add(basePath + File.separator + "app" + File.separator + "www" + File.separator + "config.xml");

        String id = "";
        for(int i = 0; i < possiblePaths.size() && id.isEmpty(); i++) {
            XmlTag widget = this.getRootTag(project, possiblePaths.get(i));
            if (widget != null) {
                id = widget.getAttributeValue("id");
            }
        }

        return id;
    }
}
