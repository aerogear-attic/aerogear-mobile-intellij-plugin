package org.aerogear.plugin.intellij.mobile.ui.certconfiguration;

import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import org.aerogear.plugin.intellij.mobile.api.CLIException;

import javax.swing.*;

public class CertPane extends JBScrollPane {

    public CertPane() throws CLIException{
        JBPanel view = new JBPanel();
        view.setLayout(new BoxLayout(view, BoxLayout.PAGE_AXIS));
        setViewportView(view);
        view.withBackground(JBColor.WHITE);
        view.add(createCertPanel());
    }

    public JPanel createCertPanel() throws CLIException {
        CertPanel certPanel = new CertPanel();
        // todo add action listener to pinned cert panel
        certPanel.getEdit().addActionListener(actionEvent -> new PinnedCertDialog().show());
        return certPanel;
    }
}
