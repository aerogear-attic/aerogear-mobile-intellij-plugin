package org.aerogear.plugin.intellij.mobile.module;

import com.intellij.uiDesigner.core.GridLayoutManager;

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
    add(clientPanel, FlowLayout.LEFT);
  }


}
