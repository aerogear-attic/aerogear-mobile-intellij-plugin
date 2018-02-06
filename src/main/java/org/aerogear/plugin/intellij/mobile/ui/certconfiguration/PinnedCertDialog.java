package org.aerogear.plugin.intellij.mobile.ui.certconfiguration;


import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import org.aerogear.plugin.intellij.mobile.ui.certconfiguration.pinnedcerts.PinnedCertPanel;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PinnedCertDialog extends DialogWrapper {
    private PinnedCertPanel centerPanel;

    public PinnedCertDialog(){
        super(null);
        init();
        setTitle("Pinned Certificates");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        centerPanel = new PinnedCertPanel();
        centerPanel.setBackground(JBColor.WHITE);
        getContentPane().setBackground(JBColor.WHITE);
        getContentPanel().setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        return centerPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JComponent southPanel = super.createSouthPanel();
        southPanel.setBorder(BorderFactory.createMatteBorder(2,0,0,5, JBColor.WHITE));
        southPanel.setBackground(JBColor.WHITE);
        return  southPanel;
    }
}
