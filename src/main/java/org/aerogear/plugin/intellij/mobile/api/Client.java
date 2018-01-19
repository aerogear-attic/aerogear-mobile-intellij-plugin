package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import org.aerogear.plugin.intellij.mobile.api.models.MobileServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public Client() {

    }

    public MobileServices getServices() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("mobile", "get", "services", "-o=json");
        Process p = pb.start();
        BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while((line = bf.readLine()) != null) {
            sb.append(line);
        }
        Gson gson = new Gson();
        MobileServices services = gson.fromJson(sb.toString(), MobileServices.class);
        return services;
    }
}