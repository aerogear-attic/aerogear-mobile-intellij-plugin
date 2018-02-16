package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlTag;

import java.io.File;

public class CordovaConfig extends ConfigurationXml{

    public CordovaConfig() {}

    public String getId(Project project) {
        String id = "";
        String basePath = project.getBasePath() + File.separator + "app" + File.separator;
        XmlTag widget = this.getRootTag(project,basePath + "config.xml");
        if (widget != null) {
            id = widget.getAttributeValue("id");
        }

        if (id.isEmpty()) {
            widget = this.getRootTag(project,basePath + "www" + File.separator +"config.xml");
            if (widget != null) {
                id = widget.getAttributeValue("id");
            }
        }

        return id;
    }
}
