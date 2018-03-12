package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MobileAPI {

    private final CLIRunner cliRunner;

    public MobileAPI(CLIRunner cliRunner) {
        this.cliRunner = cliRunner;
    }

    public MobileServices getServices(String namespace) throws CLIException {
        List<String> cmd = preparePluginCmd(Arrays.asList("get", "services", "--namespace=" + namespace, "--", "-o=json"));
        String outPut = cliRunner.executeSync(cmd);
        Gson gson = new Gson();
        try {
            return gson.fromJson(outPut, MobileServices.class);
        } catch (JsonSyntaxException e) {
            throw new CLIException("unexpected response from CLI: " + outPut);
        }
    }

    public void createService(ServiceClass sc, List<String> params, String namespace, Watcher w) {
        List<String> cmd = preparePluginCmd(Arrays.asList("create", "serviceinstance", sc.getServiceName(), "--namespace=" + namespace, "--"));
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
        List<String> cmd = preparePluginCmd(Arrays.asList("create", "client", "--namespace=" + namespace, "--", name, clientType, bundleID, "-o=json"));
        String res = cliRunner.executeSync(cmd);

        return getMobileClientFromRes(res);
    }

    public MobileClient getClient(String clientId, String namespace) throws CLIException {
        if (clientId.isEmpty()) {
            throw new CLIException("Expected a client ID");
        }
        List<String> cmd = preparePluginCmd(Arrays.asList("get", "client", "--namespace=" + namespace, "--", clientId, "-o=json"));
        String res = cliRunner.executeSync(cmd);

        return getMobileClientFromRes(res);
    }

    public String getClientConfig(String clientId, String namespace) throws CLIException {
        List<String> cmd = preparePluginCmd(Arrays.asList("get", "clientconfig", "--namespace=" + namespace, "--", clientId, "-o=json"));
        return cliRunner.executeSync(cmd);
    }

    public String ocLogin(String url, String login, String password, boolean tlsVerify){
        List<String> cmd = prepareOcCmd(Arrays.asList("login", url, "--username=" + login, "--password=" + password, "--insecure-skip-tls-verify=" + tlsVerify));
        return cliRunner.executeSync(cmd);
    }

    public String getOpenshiftToken(){
        List<String> cmd = prepareOcCmd(Arrays.asList("whoami", "-t"));
        return cliRunner.executeSync(cmd);
    }

    private MobileClient getMobileClientFromRes(String res) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(res, MobileClient.class);
        } catch (JsonSyntaxException e) {
            throw new CLIException("Unexpected response from CLI: " + res);
        }
    }

    private List<String> prepareOcCmd(@NotNull List<String> args) {
        List<String> cmd = new ArrayList<>();
        cmd.add("oc");
        cmd.addAll(args);

        return cmd;
    }

    private List<String> preparePluginCmd(@NotNull List<String> args) {
        List<String> cmd = new ArrayList<>();
        cmd.add("oc");
        cmd.add("plugin");
        cmd.add("mobile");
        cmd.addAll(args);

        return cmd;
    }
}
