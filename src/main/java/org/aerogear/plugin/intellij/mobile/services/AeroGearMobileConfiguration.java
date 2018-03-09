package org.aerogear.plugin.intellij.mobile.services;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.aerogear.plugin.intellij.mobile.ui.configuretarget.TargetConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * PersistentStateComponent keeps project config values.
 */
@State(
        name="AeroGearMobileConfiguration",
        storages = {
                @Storage("AeroGearMobileConfiguration.xml")
        }
)

public class AeroGearMobileConfiguration implements PersistentStateComponent<AeroGearMobileConfiguration> {
    private String configPath;
    private String clientName;

    private String openshiftUrl;
    private String openshiftLogin;
    private String openshiftToken;
    private String openshiftNamespace;

    @Nullable
    @Override
    public AeroGearMobileConfiguration getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull AeroGearMobileConfiguration state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Nullable
    public static AeroGearMobileConfiguration getInstance(Project project) {
        return ServiceManager.getService(project, AeroGearMobileConfiguration.class);
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return this.clientName;
    }

    public String getOpenshiftUrl() {
        return openshiftUrl;
    }

    public void setOpenshiftUrl(String openshiftUrl) {
        this.openshiftUrl = openshiftUrl;
    }

    public String getOpenshiftLogin() {
        return openshiftLogin;
    }

    public void setOpenshiftLogin(String openshiftLogin) {
        this.openshiftLogin = openshiftLogin;
    }

    public String getOpenshiftToken() {
        return openshiftToken;
    }

    public void setOpenshiftToken(String openshiftToken) {
        this.openshiftToken = openshiftToken;
    }

    public String getOpenshiftNamespace() {
        return openshiftNamespace;
    }

    public void setOpenshiftNamespace(String openshiftNamespace) {
        this.openshiftNamespace = openshiftNamespace;
    }

    public void setFromTargetConfig(TargetConfig targetConfig){
        setOpenshiftUrl(targetConfig.getUrl());
        setOpenshiftLogin(targetConfig.getLogin());
        setOpenshiftToken(targetConfig.getToken());
        setOpenshiftNamespace(targetConfig.getNamespace());
    }

    public TargetConfig getTargetConfig(){
        return new TargetConfig(this.openshiftUrl, this.openshiftLogin, "", this.openshiftToken, this.openshiftNamespace);
    }


}

