package org.aerogear.plugin.intellij.mobile.ui.cmd;

import com.google.gson.Gson;
import org.aerogear.plugin.intellij.mobile.ui.cmd.services.MobileServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandBuilder {

    public CommandBuilder() {

    }

    public void getServices() throws IOException {
        System.out.println("Getting services...");
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
    }
}