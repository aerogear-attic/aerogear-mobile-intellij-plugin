package org.aerogear.plugin.intellij.mobile.wizard;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunnerImpl;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;

import javax.swing.JComponent;


class CreateClientFirstStep extends ModuleWizardStep {
    private CreateClientForm clientForm;
    private CreateClientFormInputs clientFormInputs;
    private MobileAPI mobileAPI;
    private CreateClientBuilder createClientBuilder;


    CreateClientFirstStep(CreateClientBuilder createClientBuilder) {
        this.createClientBuilder = createClientBuilder;
        clientForm = new CreateClientForm();
        mobileAPI = new MobileAPI(new CLIRunnerImpl());
    }

    @Override
    public JComponent getComponent() {
        return clientForm;
    }

    @Override
    public void updateDataModel() {
        String output;
        try {
            MobileClient mobileClient = mobileAPI.createClient(
                    clientFormInputs.getName(),
                    clientFormInputs.getClientType(),
                    clientFormInputs.getAppIdentifier()
            );
            output = mobileClient.getSpec().toString();

        } catch (CLIException e) {
            output = e.getMessage();
        }
        createClientBuilder.setCommandOutput(output);
        System.out.println(output);
    }

    @Override
    public boolean validate() {
        clientFormInputs = clientForm.getInputs();
        clientForm.resetValidationNotifications();
        boolean validName = true;
        boolean validId = true;

        if (clientFormInputs.isInvalidName()) {
            clientForm.invalidNameNotify();
            validName = false;
        }

        if (clientFormInputs.isInvalidAppIdentifier()) {
            clientForm.invalidAppIdNotify();
            validId = false;
        }

        return validName && validId;
    }
}
