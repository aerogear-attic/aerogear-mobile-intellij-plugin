package org.aerogear.plugin.intellij.mobile.services.sdkconfig;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import java.io.File;
import java.util.Objects;

class WriteToFileRunnable implements Runnable {

    private final Project project;
    private final String path;
    private final CharSequence cs;


    WriteToFileRunnable(Project project, String path, CharSequence cs) {
        this.project = project;
        this.path = path;
        this.cs = cs;
    }

    @Override
    public void run() {
        File configFile = new File(this.path);
        VirtualFile vConfig = LocalFileSystem.getInstance().findFileByIoFile(configFile);
        PsiFile psiConfig = PsiManager.getInstance(this.project).findFile(Objects.requireNonNull(vConfig));
        Document d =  PsiDocumentManager.getInstance(this.project).getDocument(Objects.requireNonNull(psiConfig));
        Objects.requireNonNull(d).setText(this.cs);
    }
}
