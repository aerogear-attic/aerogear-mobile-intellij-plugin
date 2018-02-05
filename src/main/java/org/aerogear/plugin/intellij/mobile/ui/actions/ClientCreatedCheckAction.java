package org.aerogear.plugin.intellij.mobile.ui.actions;

import com.intellij.ide.BrowserUtil;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.actions.ShowFilePathAction;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileEditor.ex.FileEditorProviderManager;
import com.intellij.openapi.fileEditor.impl.NonProjectFileWritingAccessProvider;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.newEditor.SettingsDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.aerogear.plugin.intellij.mobile.ui.MobileIcons;
import org.aerogear.plugin.intellij.mobile.ui.createclientpopup.CreateClientForm;
import org.aerogear.plugin.intellij.mobile.ui.createclientpopup.TestDialog;
import org.jdesktop.swingx.action.ActionManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.Action;
import javax.swing.event.HyperlinkEvent;
import java.io.File;
import java.util.Set;


public class ClientCreatedCheckAction implements StartupActivity{
    private static final String dotFilePath = ".aerogear";
    @Override
    public void runActivity(@NotNull Project project) {
        String projectPath = project.getBasePath();
        openFile(projectPath + '/' + dotFilePath, project);
    }


    public static void openFile(String filePath, @NotNull Project project) {
        VirtualFile file = LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath);
        MobileNotificationsService mobileNotificationsService = ServiceManager.getService(MobileNotificationsService.class);
        if (file == null) {
            System.out.println(" mobile client file doesn't exist");

            Notifications.Bus.notify(new Notification(
                    "Aerogear Mobile Notifications",
                    MobileIcons.AEROGEAR,
                    "<html>TLogin failed",
                    " Go to <a href=\"" + "asd" + "\" target=\"blank\">Settings</a> to setup login data!</html>",
                    null,
                    NotificationType.ERROR,
                    (notification, event) -> {
                        TestDialog td = new TestDialog();

                        DialogBuilder dialogBuilder = new DialogBuilder();
                        dialogBuilder.setCenterPanel(td.getRootPane());
                        dialogBuilder.removeAllActions();
                        dialogBuilder.addOkAction();
                        dialogBuilder.addCancelAction();
                        dialogBuilder.show();


                        System.out.println("asdasdasd");
                        boolean isOk = dialogBuilder.show() == DialogWrapper.OK_EXIT_CODE;
                        td.dispose();
                        if (isOk) {
                            System.out.println(">>>>>" + isOk);
                        }
                    }),
                    project);
        }

        else if (file != null && file.isValid()) {
            openFile(file, project);
        }


    }

    public static void openFile(VirtualFile file, @NotNull Project project) {
        FileEditorProvider[] providers = FileEditorProviderManager.getInstance().getProviders(project, file);
        if (providers.length == 0) {
            String message = IdeBundle.message("error.files.of.this.type.cannot.be.opened", ApplicationNamesInfo.getInstance().getProductName());
            Messages.showErrorDialog(project, message, IdeBundle.message("title.cannot.open.file"));
            return;
        }

        NonProjectFileWritingAccessProvider.allowWriting(file);

        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file);
        FileEditorManager.getInstance(project).openTextEditor(descriptor, true);
    }


}
