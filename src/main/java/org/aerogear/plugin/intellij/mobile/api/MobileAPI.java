package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MobileAPI {

    public MobileAPI() {
    }

    public MobileServices getServices() {
        ProcessBuilder pb = new ProcessBuilder("mobile", "get", "services", "-o=json");
        StringBuilder sb = new StringBuilder();

        try {
            Process p = pb.start();
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while((line = bf.readLine()) != null) {
                sb.append(line);
            }
        } catch(Exception exception) {
            System.out.println(exception);
        }

        Gson gson = new Gson();
        MobileServices services = gson.fromJson(sb.toString(), MobileServices.class);
        return services;
    }
}