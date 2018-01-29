package org.aerogear.plugin.intellij.mobile.wizard;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.aerogear.plugin.intellij.mobile.ui.MobileIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;


public class CreateClientType extends ModuleType<CreateClientBuilder> {

    public CreateClientType() {
        super(Constants.MODULE_ID);
    }

    public static CreateClientType getInstance() {
        return (CreateClientType) ModuleTypeManager.getInstance().findByID(Constants.MODULE_ID);
    }

    @NotNull
    @Override
    public CreateClientBuilder createModuleBuilder() {
        return new CreateClientBuilder();
    }


    @NotNull
    @Override
    public String getName() {
        return Constants.MODULE_NAME;
    }

    @NotNull
    @Override
    public String getDescription() {
        return Constants.MODULE_DESCRIPTION;
    }

    @Override
    public Icon getNodeIcon(boolean isOpened) {
        return MobileIcons.AEROGEAR;
    }

}
