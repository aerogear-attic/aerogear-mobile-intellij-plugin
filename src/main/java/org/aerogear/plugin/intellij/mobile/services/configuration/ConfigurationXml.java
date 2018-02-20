package org.aerogear.plugin.intellij.mobile.services.configuration;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlDocument;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;

import java.util.Objects;

abstract class ConfigurationXml {

    XmlTag getRootTag(Project project, String path) {
        XmlTag rootTag = null;
        VirtualFile vFile = ApplicationManager.getApplication().runReadAction(new Computable<VirtualFile>() {
            @Override
            public VirtualFile compute() {
                return LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
            }
        });

        if (vFile != null) {
            final XmlFile xmlFile = (XmlFile) PsiManager.getInstance(project).findFile(vFile);
            final XmlDocument document = Objects.requireNonNull(xmlFile).getDocument();
            if (document != null) {
                rootTag = document.getRootTag();
            }
        }

        return rootTag;
    }

    public abstract String getBundleId(Project project);

    public abstract String attrValue();
}
