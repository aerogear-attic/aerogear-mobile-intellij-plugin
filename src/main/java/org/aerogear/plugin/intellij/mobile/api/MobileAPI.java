package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MobileAPI {

    public MobileAPI() {}

    public MobileServices getServices() throws CLIException {
        List<String> command = new ArrayList<String>();
        command.add("mobile");
        command.add("get");
        command.add("services");
        command.add("-o=json");
        ProcessBuilder pb = new ProcessBuilder(command);
        MobileServices services = null;

        try {
            Process p = pb.start();
            StringBuilder input = getResult(p.getInputStream());
            StringBuilder error = getResult(p.getErrorStream());
            if (input.length() != 0) {
                services = new Gson().fromJson(input.toString(), MobileServices.class);
            } else if (error.length() != 0) {
                throw new CLIException("Failed to retrieve mobile services from mobile cli", new Throwable(error.toString()));
            }
        } catch (IOException e) {
            throw new CLIException("Failed to retrieve mobile services from mobile cli", e.getCause());
        }

        return services;
    }

    private StringBuilder getResult(InputStream s) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(s));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (bf != null) bf.close();
            } catch (Exception e) {
                throw e;
            }

        }

        return sb;
    }

    private void watch(List<String> command, Watch w) {
        ProcessBuilder pb = new ProcessBuilder(command);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(() -> {
                try {
                    Process p = pb.start();
                    StringBuilder input = getResult(p.getInputStream());
                    StringBuilder error = getResult(p.getErrorStream());
                    if (input.length() != 0) {
                        w.onSuccess(input);
                    } else if (error.length() != 0) {
                        w.onError(new Exception(error.toString()));
                    }
                } catch (IOException e) {
                    w.onError(e);
                }
        });
    }

    public void createService(ServiceClass sc, List<String> params, Watch w) {
        List<String> command = new ArrayList<String>();
        command.add("mobile");
        command.add("create");
        command.add("serviceinstance");
        command.add(sc.getServiceName());
        for (String param : params) {
            command.add(param);
        }

        this.watch(command, w);
    }
}