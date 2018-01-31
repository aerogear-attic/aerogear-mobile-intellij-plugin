package org.aerogear.plugin.intellij.mobile.wizard;

public class CreateClientFormInputs {
    private String name;
    private String clientType;
    private String appIdentifier;


    CreateClientFormInputs() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getAppIdentifier() {
        return appIdentifier;
    }

    public void setAppIdentifier(String appIdentifier) {
        this.appIdentifier = appIdentifier;
    }

}
