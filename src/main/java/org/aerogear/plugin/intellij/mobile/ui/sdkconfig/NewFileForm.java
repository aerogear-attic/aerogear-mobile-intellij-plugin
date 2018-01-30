package org.aerogear.plugin.intellij.mobile.ui.sdkconfig;

import javax.swing.JTextField;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

public class NewFileForm extends javax.swing.JPanel {

    public NewFileForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        destinationLabel = new javax.swing.JLabel();
        fileNamelabel = new javax.swing.JLabel();
        destinationInput = new TextFieldWithBrowseButton();
        fileNameInput = new javax.swing.JTextField();

        destinationLabel.setText("Destination directory:");

        fileNamelabel.setText("File name:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(destinationLabel)
                                        .addComponent(fileNamelabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(destinationInput, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                                        .addComponent(fileNameInput))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(destinationLabel)
                                        .addComponent(destinationInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fileNamelabel)
                                        .addComponent(fileNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>



    private TextFieldWithBrowseButton destinationInput;
    private javax.swing.JLabel destinationLabel;
    private javax.swing.JTextField fileNameInput;
    private javax.swing.JLabel fileNamelabel;
    private javax.swing.JPanel jPanel1;

    public TextFieldWithBrowseButton getDestinationInput() {
        return destinationInput;
    }

    public JTextField getFileNameInput() {
        return fileNameInput;
    }
}
