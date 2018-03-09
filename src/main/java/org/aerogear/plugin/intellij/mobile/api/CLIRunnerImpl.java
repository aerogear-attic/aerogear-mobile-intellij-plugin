package org.aerogear.plugin.intellij.mobile.api;

import org.aerogear.plugin.intellij.mobile.services.MobileNotificationsService;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CLIRunnerImpl implements CLIRunner {

  private static final String OUTPUT_TYPE_ERROR = "error";
  private static final String OUTPUT_TYPE_STD = "std";

  private static final int CMD_TIMEOUT_SECONDS = 10;
  private static final int EXECUTOR_NUM_THREADS = 3;

  private final MobileNotificationsService notificationsService = MobileNotificationsService.getInstance();
  private final ExecutorService executorService;

  private static final CLIRunner instance = new CLIRunnerImpl();

  private CLIRunnerImpl() {
    this.executorService = Executors.newFixedThreadPool(EXECUTOR_NUM_THREADS);
  }

  @Override
  public String executeSync(List<String> args) throws CLIException {
    return executeSync(args, true);
  }

  @Override
  public String executeSync(List<String> args, boolean plugin) throws CLIException {
    List<String> cmd = prepareCmd(args, plugin);
    String outPut;
    Process p = null;
    try {
      ProcessBuilder pb = new ProcessBuilder(cmd);
      p = pb.start();
      if (!p.waitFor(CMD_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
        //timed out
        throw new CLIException("Timed out waiting for the list of services. Is your OpenShift cluster running?");
      }
      if (p.exitValue() != 0) {
        outPut = readOutput(OUTPUT_TYPE_ERROR,p.getErrorStream(), false);
        throw new CLIException(outPut);
      }
      outPut = readOutput(OUTPUT_TYPE_STD,p.getInputStream(),false);
    } catch (IOException e) {
      throw new CLIException("Unexpected IO error executing CLI command: " + e.getMessage(), e.getCause());
    } catch (InterruptedException e) {
      throw new CLIException("CLI command was unexpectedly interrupted: " + e.getMessage(), e.getCause());
    } catch (Exception e){
      throw new CLIException("Unexpected exception executing CLI command: " + e.getMessage(), e.getCause());
    }finally {
      if (p != null)
        p.destroyForcibly();
    }
    return outPut;
  }



  private List<String> prepareCmd(@NotNull List<String> args, boolean plugin) {
    List<String> cmd = new ArrayList<>();
    cmd.add("oc");

    if (plugin){
      cmd.add("plugin");
      cmd.add("mobile");
    }

    cmd.addAll(args);

    return cmd;
  }

  @Override
  public void executeAsync(List<String> args, Watcher w) {
      executeAsync(args, true, w);
  }

  @Override
  public void executeAsync(List<String> args, boolean plugin, Watcher w) {
    executorService.execute(() -> {
          List<String> cmd = prepareCmd(args, plugin);
          ProcessBuilder pb = new ProcessBuilder(cmd);
          try {
            final Process p = pb.start();
            boolean finished = p.waitFor(CMD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            Callable<String> inputRead = () -> readOutput(OUTPUT_TYPE_STD,p.getInputStream(), true);
            Callable<String> errorRead = () -> readOutput(OUTPUT_TYPE_ERROR,p.getErrorStream(),true);
            Future<String>  cmdOut = executorService.submit(inputRead);
            Future<String>  cmdErr = executorService.submit(errorRead);
            String out = cmdOut.get();
            String error = cmdErr.get();
            if (!error.isEmpty()) {
              w.onError(new CLIException(error));
            }
            else if (!out.isEmpty()) {
              w.onSuccess(out);
            }
            if (!finished) {
              p.destroy();
              p.waitFor();
            }
          } catch (Exception e) {
            w.onError(e);
          }
        });
  }

  @NotNull
  private String readOutput(String outputType,InputStream in, Boolean shouldNotify) throws IOException {
    StringBuilder sb = new StringBuilder();

    try (BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {
      String line;
      while ((line = bf.readLine()) != null) {
        sb.append(line).append(System.lineSeparator());
      }
    }

    String out =  sb.toString();
    if (shouldNotify) {
      if (outputType.equals(OUTPUT_TYPE_ERROR)) {
        this.notificationsService.notifyError("CLI error", out);
      } else {
        this.notificationsService.notifyInformation("CLI output", out);
      }
    }
    return out;
  }

  public static CLIRunner getInstance() {
    return instance;
  }
}
