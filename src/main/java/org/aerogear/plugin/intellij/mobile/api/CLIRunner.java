package org.aerogear.plugin.intellij.mobile.api;

import java.util.List;

public interface CLIRunner {
  String executeCmd(List<String> command)throws CLIException;
  void executeAndWatch(List<String> command, Watch w);
}
