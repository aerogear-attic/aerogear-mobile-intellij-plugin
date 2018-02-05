package org.aerogear.plugin.intellij.mobile.ui.createclientpopup;

/**
 * Store of data inputs in the create client form
 */
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

    /**
     * validates client name
     * @return true - name is invalid, false - valid
     */
    public boolean isInvalidName(){
        return name.isEmpty();
    }

    /**
     * validates app id
     * @return true - app id is invalid, false - valid
     */
    public boolean isInvalidAppIdentifier(){
        return appIdentifier.isEmpty();
    }

}