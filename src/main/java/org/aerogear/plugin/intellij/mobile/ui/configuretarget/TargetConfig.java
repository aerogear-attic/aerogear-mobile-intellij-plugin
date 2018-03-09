package org.aerogear.plugin.intellij.mobile.ui.configuretarget;

import com.google.common.base.Objects;

public class TargetConfig {
  private String url;
  private String login;
  private String password;
  private String token;
  private String namespace;

  public TargetConfig(String url, String login, String password, String token, String namespace) {
    this.url = url;
    this.login = login;
    this.password = password;
    this.token = token;
    this.namespace = namespace;
  }

  public String getUrl() {
    return url;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public String getToken() {
    return token;
  }

  public String getNamespace() {
    return namespace;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TargetConfig that = (TargetConfig) o;
    return Objects.equal(url, that.url) &&
            Objects.equal(login, that.login) &&
            Objects.equal(token, that.token) &&
            Objects.equal(namespace, that.namespace);
  }

}
//TODO
//token (SHA) oc whoami -t
//namespace?
//if pwd -> oc login to get token and save that