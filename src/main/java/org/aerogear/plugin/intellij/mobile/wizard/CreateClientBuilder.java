package org.aerogear.plugin.intellij.mobile.wizard;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleBuilderListener;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class CreateClientBuilder extends ModuleBuilder implements ModuleBuilderListener {
    private String commandOutput;

    @Override
    public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
    }

    @Override
    public ModuleType getModuleType() {
        return CreateClientType.getInstance();
    }


    @Nullable
    @Override
    public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
        return new CreateClientFirstStep(this);
    }


    @Override
    public void moduleCreated(@NotNull Module module) {
        //TODO what do we do after the wizard has been created
    }


    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{new CreateClientSecondStep(this)};
    }

    public String getCommandOutput() {
        return commandOutput;
    }

    public void setCommandOutput(String commandOutput) {
        this.commandOutput = commandOutput;
    }
}
