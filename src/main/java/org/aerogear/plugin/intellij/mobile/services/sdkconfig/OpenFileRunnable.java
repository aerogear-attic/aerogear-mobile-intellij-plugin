package org.aerogear.plugin.intellij.mobile.services.sdkconfig;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

public class OpenFileRunnable implements Runnable {

    private Project project;
    private String path;


    public OpenFileRunnable(Project project, String path) {
        this.project = project;
        this.path = path;
    }

    @Override
    public void run() {
        File file = new File(this.path);
        VirtualFile vConfig = LocalFileSystem.getInstance().findFileByIoFile(file);
        new OpenFileDescriptor(project, vConfig).navigate(true);
    }
}
