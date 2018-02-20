package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MobileAPI {

    private final CLIRunner cliRunner;

    public MobileAPI(CLIRunner cliRunner) {
        this.cliRunner = cliRunner;
    }

    public MobileServices getServices(String namespace) throws CLIException {
        String outPut = cliRunner.executeSync(Arrays.asList("get", "services", "--namespace=" + namespace, "--", "-o=json"));
        Gson gson = new Gson();
        try {
            return gson.fromJson(outPut, MobileServices.class);
        } catch (JsonSyntaxException e) {
            throw new CLIException("unexpected response from CLI: " + outPut);
        }
    }

    public void createService(ServiceClass sc, List<String> params, String namespace, Watcher w) {
        List<String> cmd = new ArrayList<>(Arrays.asList("create", "serviceinstance", sc.getServiceName(), "--namespace=" + namespace, "--"));
        for (String param : params) {
            cmd.add("-p");
            cmd.add(param);
        }
        cliRunner.executeAsync(cmd, w);
    }

    public MobileClient createClient(String name, String clientType, String bundleID, String namespace) throws CLIException {
        if (name.isEmpty() || clientType.isEmpty() || bundleID.isEmpty()) {
            throw new CLIException("Expected a client name, a client type and a bundle id");
        }
        String res = cliRunner.executeSync(Arrays.asList("create", "client", "--namespace=" + namespace, "--", name, clientType, bundleID, "-o=json"));

        return getMobileClientFromRes(res);
    }

    public MobileClient getClient(String clientId, String namespace) throws CLIException {
        if (clientId.isEmpty()) {
            throw new CLIException("Expected a client ID");
        }
        String res = cliRunner.executeSync(Arrays.asList("get", "client", "--namespace=" + namespace, "--", clientId, "-o=json"));
        return getMobileClientFromRes(res);
    }

    private MobileClient getMobileClientFromRes(String res) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(res, MobileClient.class);
        } catch (JsonSyntaxException e) {
            throw new CLIException("Unexpected response from CLI: " + res);
        }
    }

    public String getClientConfig(String clientId, String namespace) throws CLIException {
        return cliRunner.executeSync(
                Arrays.asList("get", "clientconfig", "--namespace=" + namespace, "--", clientId, "-o=json")
        );
    }
}
