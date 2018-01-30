package org.aerogear.plugin.intellij.mobile.models;

public class MobileClientSpec {
  
  public String name;
  public String apiKey;
  public String clientType;
  public String appIdentifier;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getApiKey() {
    return apiKey;
  }
  
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
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
  
  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    
    MobileClientSpec that = (MobileClientSpec) o;
    
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;
    if (apiKey != null ? !apiKey.equals(that.apiKey) : that.apiKey != null)
      return false;
    if (clientType != null ? !clientType.equals(that.clientType) : that.clientType != null)
      return false;
    return appIdentifier != null ? appIdentifier.equals(that.appIdentifier) : that.appIdentifier == null;
  }
  
  @Override public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (apiKey != null ? apiKey.hashCode() : 0);
    result = 31 * result + (clientType != null ? clientType.hashCode() : 0);
    result = 31 * result + (appIdentifier != null ? appIdentifier.hashCode() : 0);
    return result;
  }
}
