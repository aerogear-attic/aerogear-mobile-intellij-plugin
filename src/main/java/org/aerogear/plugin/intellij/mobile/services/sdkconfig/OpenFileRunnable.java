package org.aerogear.plugin.intellij.mobile.services.sdkconfig;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;
import java.util.Objects;

class OpenFileRunnable implements Runnable {

    private final Project project;
    private final String path;


    OpenFileRunnable(Project project, String path) {
        this.project = project;
        this.path = path;
    }

    @Override
    public void run() {
        File file = new File(this.path);
        VirtualFile vConfig = LocalFileSystem.getInstance().findFileByIoFile(file);
        new OpenFileDescriptor(project, Objects.requireNonNull(vConfig)).navigate(true);
    }
}
