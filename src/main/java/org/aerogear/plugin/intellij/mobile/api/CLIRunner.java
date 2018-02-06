package org.aerogear.plugin.intellij.mobile.api;

import java.util.List;

public interface CLIRunner {
  String executeSync(List<String> command) throws CLIException;

  void executeAsync(List<String> command, Watcher w);
}
