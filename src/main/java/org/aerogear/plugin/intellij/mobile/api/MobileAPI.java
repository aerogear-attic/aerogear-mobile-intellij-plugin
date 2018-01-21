package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MobileAPI {

    public MobileAPI() {}

    public MobileServices getServices() throws CLIException{
        ProcessBuilder pb = new ProcessBuilder("mobile", "get", "services", "-o=json");
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = null;

        try {
            Process p = pb.start();
            bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while((line = bf.readLine()) != null) {
                sb.append(line);
            }
        } catch(Exception e) {
            throw new CLIException("Failed to retrieve mobile services from mobile cli", e.getCause());
        } finally {
            try {
                if (bf != null) bf.close();
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        Gson gson = new Gson();
        MobileServices services = gson.fromJson(sb.toString(), MobileServices.class);
        return services;
    }

    public void createService(ServiceClass sc, List<String> params) {
        List<String> command = new ArrayList<String>();
        command.add("mobile");
        command.add("create");
        command.add("serviceinstance");
        command.add(sc.getServiceName());
        command.add("--namespace=myproject");
        for (String param : params) {
            command.add("-p " + param);
        }
        ProcessBuilder pb = new ProcessBuilder(command);
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}