package org.aerogear.plugin.intellij.mobile.api;

import java.util.List;

public interface CLIRunner {
  String runCmd(List<String> cmd, int timeoutInSeconds)throws CLIException;
  List<String>prepareCmd(String... args);
  void runAndWatch(List<String> command, Watch w);
}
