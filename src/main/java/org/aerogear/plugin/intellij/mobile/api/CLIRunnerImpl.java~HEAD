package org.aerogear.plugin.intellij.mobile.api;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CLIRunnerImpl implements CLIRunner {

  private static int CMD_TIMEOUT_SECONDS = 10;

  @Override
  public String executeSync(List<String> args) throws CLIException {
    List<String> cmd = prepareCmd(args);
    String outPut;
    Process p = null;
    try {
      ProcessBuilder pb = new ProcessBuilder(cmd);
      p = pb.start();
      if (!p.waitFor(CMD_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
        //timed out
        throw new CLIException("timed out waiting for the list of services. Is your OpenShift cluster running?");
      }
      if (p.exitValue() != 0) {
        outPut = readOutput(p.getErrorStream());
        throw new CLIException(outPut);
      }
      outPut = readOutput(p.getInputStream());
    } catch (IOException e) {
      throw new CLIException("unexpected io error executing cli command : " + e.getMessage(), e.getCause());
    } catch (InterruptedException e) {
      throw new CLIException("cli command was unexpectedly interrupted : " + e.getMessage(), e.getCause());
    } finally {
      if (p != null)
        p.destroyForcibly();
    }
    return outPut;
  }

  private List<String> prepareCmd(@NotNull List<String> args) {
    List<String> cmd = new ArrayList<>();
    cmd.add("oc");
    cmd.add("plugin");
    cmd.add("mobile");
    for (String a : args) {
      cmd.add(a);
    }
    return cmd;
  }

  public void executeAsync(List<String> command, Watcher w) {
    Executors.newSingleThreadExecutor()
        .execute(() -> {
          ProcessBuilder pb = new ProcessBuilder(command);
          Process p = null;
          try {
            p = pb.start();
            String input = readOutput(p.getInputStream());
            String error = readOutput(p.getErrorStream());
            if (!input.isEmpty()) {
              w.onSuccess(input);
            } else if (!error.isEmpty()) {
              w.onError(new CLIException(error));
            }
          } catch (IOException e) {
            w.onError(e);
          } finally {
            if (p != null)
              p.destroyForcibly();
          }
        });
  }

  @NotNull
  private String readOutput(InputStream in) throws IOException {
    StringBuilder sb = new StringBuilder();

    try (BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {
      String line;
      while ((line = bf.readLine()) != null) {
        sb.append(line);
      }
    }
    return sb.toString();
  }
}
