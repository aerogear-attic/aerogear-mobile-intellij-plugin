package org.aerogear.plugin.intellij.mobile.module;

import javax.swing.*;
import java.awt.*;

public class CreateClientForm extends JPanel{
  private JPanel clientPanel;
  private JTextField clientNameTxtField;
  private JComboBox clientTypescomboBox;
  private JLabel clientNameLabel;
  private JLabel clientTypeLabel;

  public CreateClientForm() {
    super();
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    add(clientPanel);
  }


}
