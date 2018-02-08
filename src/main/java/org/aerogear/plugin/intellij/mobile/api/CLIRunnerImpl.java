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
  
  MobileNotificationsService notificationsService;
  
  public CLIRunnerImpl() {
    this.notificationsService = new MobileNotificationsService();
  }
  
  private final String OUTPUT_TYPE_ERROR = "error";
  private final String OUTPUT_TYPE_STD = "std";
  
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
        outPut = readOutput(OUTPUT_TYPE_ERROR,p.getErrorStream(), false);
        throw new CLIException(outPut);
      }
      outPut = readOutput(OUTPUT_TYPE_STD,p.getInputStream(),false);
    } catch (IOException e) {
      throw new CLIException("unexpected io error executing cli command : " + e.getMessage(), e.getCause());
    } catch (InterruptedException e) {
      throw new CLIException("cli command was unexpectedly interrupted : " + e.getMessage(), e.getCause());
    } catch (Exception e){
      throw new CLIException("unexpected exception executing cli command : " + e.getMessage(), e.getCause());
    }finally {
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

    cmd.addAll(args);

    return cmd;
  }

  public void executeAsync(List<String> args, Watcher w) {
    final ExecutorService ex = Executors.newFixedThreadPool(3);
    List<String> cmd = prepareCmd(args);
    ex.execute(() -> {
          ProcessBuilder pb = new ProcessBuilder(cmd);
        
          try {
            final Process p = pb.start();
            Callable<String> inputRead = new Callable<String>() {
              @Override public String call() throws Exception {
                return readOutput(OUTPUT_TYPE_STD,p.getInputStream(), true);
              }
            };
            Callable<String> errorRead = new Callable<String>() {
              @Override public String call() throws Exception {
                return readOutput(OUTPUT_TYPE_ERROR,p.getErrorStream(),true);
              }
            } ;
            Future<String>  cmdInput = ex.submit(inputRead);
            Future<String>  cmdErr = Executors.newSingleThreadExecutor().submit(errorRead);
            String input = cmdInput.get();
            String error = cmdErr.get();
            if (!error.isEmpty()) {
              w.onError(new CLIException(error));
            }
            else if (!input.isEmpty()) {
              w.onSuccess(input);
            } else
            p.destroyForcibly();
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
        if (outputType.equals("error") && shouldNotify){
          this.notificationsService.notifyError("cli error",line);
        } else if (shouldNotify) {
          this.notificationsService.notifyInformation("cli output", line);
        }
        sb.append(line + "\n");
      }
    }
    return sb.toString();
  }
}
