package org.aerogear.plugin.intellij.mobile.services;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
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
}

