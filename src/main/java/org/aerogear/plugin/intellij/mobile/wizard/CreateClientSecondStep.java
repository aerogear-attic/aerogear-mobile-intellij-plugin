package org.aerogear.plugin.intellij.mobile.wizard;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.JComponent;
import javax.swing.JTextArea;


class CreateClientSecondStep extends ModuleWizardStep {
    private CreateClientBuilder myBuilder;
    private JTextArea textArea;

    CreateClientSecondStep(CreateClientBuilder myBuilder) {
        this.myBuilder = myBuilder;
        textArea = new JTextArea("");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
    }


    @Override
    public JComponent getComponent() {
        textArea.setText(myBuilder.getCommandOutput());
        return textArea;
    }

    @Override
    public void updateDataModel() {

    }

    public boolean validate() {
        boolean hasError = textArea.getText().toLowerCase().startsWith("error");
        if (hasError) {
            textArea.append("\nError: go back to previous screen and fix your inputs.");
            return false;
        }
        return true;
    }


}
