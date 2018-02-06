package org.aerogear.plugin.intellij.mobile.utils;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.file.PsiDirectoryFactory;


public class WriteToFileRunnable implements Runnable {

  private CharSequence charSeq;
  private String fileName;
  private FileType fileType;
  private String path;
  private Project project;


  public WriteToFileRunnable(Project project, String path, CharSequence cs, String fileName, FileType fileType) {
    this.charSeq = cs;
    this.fileName = fileName;
    this.fileType = fileType;
    this.path = path;
    this.project = project;
  }

  @Override
  public void run() {
    VirtualFile existingFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(this.path);

    if (existingFile == null) {
      PsiFile ourFile = PsiFileFactory.getInstance(project).createFileFromText(fileName, this.fileType, this.charSeq);
      VirtualFile projectBasePath = project.getBaseDir();
      PsiDirectoryFactory psiDirectoryFactory = PsiDirectoryFactory.getInstance(project);
      PsiDirectory psiDirectory = psiDirectoryFactory.createDirectory(projectBasePath);
      psiDirectory.add(ourFile);

    } else {
      PsiFile psiFile = PsiManager.getInstance(this.project).findFile(existingFile);
      Document document = PsiDocumentManager.getInstance(this.project).getDocument(psiFile);
      document.setText(this.charSeq);
    }

  }
}