package org.aerogear.plugin.intellij.mobile.models;

public class MobileClient {
  public MobileClientSpec spec;
  
  public MobileClientSpec getSpec() {
    return spec;
  }
  
  public void setSpec(MobileClientSpec spec) {
    this.spec = spec;
  }
  
  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    
    MobileClient that = (MobileClient) o;
  
    return spec != null ? spec.equals(that.spec) : that.spec == null;
  }
  
  @Override public int hashCode() {
    return spec != null ? spec.hashCode() : 0;
  }
}
