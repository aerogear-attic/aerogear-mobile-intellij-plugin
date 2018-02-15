package org.aerogear.plugin.intellij.mobile.ui.createclientpopup;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.intellij.openapi.ui.ComboBox;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;
import org.jetbrains.annotations.Nullable;


public class CreateClientForm extends DialogWrapper {
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
  private final MobileAPI mobileAPI;

  public CreateClientForm(@Nullable Project project, MobileAPI mobileAPI) {
    super(project);
    this.mobileAPI = mobileAPI;
    init();
    setTitle(Constants.CREATE_CLIENT);
  }

  private void createUIComponents() {
    clientTypeComboBox = new ComboBox<>(Constants.CLIENT_TYPES);
  }

  private void getInputs() {
    CreateClientFormInputs formInputs = new CreateClientFormInputs();
    formInputs.setName(clientNameTxtField.getText());
    formInputs.setClientType(String.valueOf(clientTypeComboBox.getSelectedItem()));
    formInputs.setAppIdentifier(clientAppIdTxtField.getText());

    this.formInputs = formInputs;
  }

  @Nullable
  @Override
  protected ValidationInfo doValidate() {
    getInputs();
    //TODO duplicate client name validation
    if (formInputs.isInvalidName()) {
      return new ValidationInfo(Constants.CLIENT_APP_NAME_IS_REQUIRED, clientNameTxtField);
    }


    if (formInputs.isInvalidAppIdentifier()) {
      return new ValidationInfo(Constants.APP_IDENTIFIER_BUNDLE_ID_PACKAGE_NAME_IS_REQUIRED, clientAppIdTxtField);
    }

    String clientId = (formInputs.getName() + "-" + formInputs.getClientType()).toLowerCase();

    try {
      MobileClient mobileClient = this.mobileAPI.getClient(clientId);
      return new ValidationInfo(String.format("Client name and type duplicate: %s", mobileClient.spec.name));
    } catch (CLIException e) {
      // this is fine https://i.imgur.com/mtGc7Sl.gif
    }

    return null;
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    return clientPanel;
  }

  public String getName() {
    return formInputs.getName();
  }

  public String getClientType() {
    return formInputs.getClientType();
  }

  public String getAppId() {
    return formInputs.getAppIdentifier();
  }

}


