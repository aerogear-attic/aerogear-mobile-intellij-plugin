package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import java.util.Objects;

public class TargetConfig {
  private String url;
  private String login;

  @com.intellij.util.xmlb.annotations.Transient
  private String password;
  private String token;
  private String namespace;
  private boolean tlsEnabled;

  public TargetConfig(){}

  public TargetConfig(String url, String login, String password, boolean tlsEnabled, String token, String namespace) {
    this.url = url;
    this.login = login;
    this.password = password;
    this.tlsEnabled = tlsEnabled;
    this.token = token;
    this.namespace = namespace;
  }

  public String getUrl() {
    return url;
  }

  public String getLogin() {
    return login;
  }


  public String getToken() {
    return token;
  }

  public String getNamespace() {
    return namespace;
  }
  
  public boolean getTlsEnabled(){
      return tlsEnabled;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public boolean isTlsEnabled() {
    return tlsEnabled;
  }

  public void setTlsEnabled(boolean tlsEnabled) {
    this.tlsEnabled = tlsEnabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TargetConfig that = (TargetConfig) o;
    return Objects.equals(url, that.url) &&
            Objects.equals(login, that.login) &&
            Objects.equals(token, that.token) &&
            Objects.equals(namespace, that.namespace) &&
            Objects.equals(tlsEnabled, that.tlsEnabled);
  }
}