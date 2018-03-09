package org.aerogear.plugin.intellij.mobile.api;

import java.util.List;

public interface CLIRunner {
  String executeSync(List<String> command) throws CLIException;
  String executeSync(List<String> command, boolean plugin) throws CLIException;
  void executeAsync(List<String> command, Watcher w);
  void executeAsync(List<String> command, boolean plugin, Watcher w);
}
