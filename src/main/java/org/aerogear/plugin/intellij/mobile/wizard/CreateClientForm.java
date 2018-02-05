package org.aerogear.plugin.intellij.mobile.wizard;

import com.intellij.ui.JBColor;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import com.intellij.openapi.ui.ComboBox;


public class CreateClientForm extends JPanel {
    private JPanel clientPanel;
    private JLabel clientNameLabel;
    private JTextField clientNameTxtField;
    private JLabel clientTypeLabel;
    private JComboBox<String> clientTypeComboBox;
    private JLabel appIdLabel;
    private JTextField clientAppIdTxtField;
    private JLabel errorLabel;
    private JLabel errorMessage;
    private Border defaultClientNameBorder;
    private CreateClientFormInputs formInputs;


    CreateClientForm() {
        super();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(clientPanel);
    }

    private void createUIComponents() {
        clientTypeComboBox = new ComboBox<>(Constants.CLIENT_TYPES);
    }

    /**
     * Returns form as map <name, value>
     *
     * @return map
     */
    public CreateClientFormInputs getInputs() {
        CreateClientFormInputs formInputs = new CreateClientFormInputs();
        formInputs.setName(clientNameTxtField.getText());
        formInputs.setClientType(String.valueOf(clientTypeComboBox.getSelectedItem()));
        formInputs.setAppIdentifier(clientAppIdTxtField.getText());

        return formInputs;
    }

    /**
     * Set border RED on clientName input to indicate missing/invalid input.
     */
    public void invalidNameNotify() {
        errorLabel.setText("Error");
        errorMessage.setText("Client Name is required");
        defaultClientNameBorder = clientNameTxtField.getBorder();
        clientNameTxtField.setBorder(BorderFactory.createLineBorder(JBColor.RED, 1));
    }

    /**
     * Set default border on clientName input, to indicate valid input.
     */
    private void validNameNotify() {
        errorLabel.setText("");
        errorMessage.setText("");
        clientNameTxtField.setBorder(defaultClientNameBorder);
    }

    /**
     * Set border RED on clientName input to indicate missing/invalid input.
     */
    public void invalidAppIdNotify() {
        errorLabel.setText("Error");
        errorMessage.setText("app identifier, bundleID|packageName is required");
        defaultClientNameBorder = clientNameTxtField.getBorder();
        clientAppIdTxtField.setBorder(BorderFactory.createLineBorder(JBColor.RED, 1));
    }

    /**
     * Set default border on clientName input, to indicate valid input.
     */
    private void validAppIdNotify() {
        errorLabel.setText("");
        errorMessage.setText("");
        clientAppIdTxtField.setBorder(defaultClientNameBorder);
    }

    public void resetValidationNotifications() {
        validNameNotify();
        validAppIdNotify();
    }

}
