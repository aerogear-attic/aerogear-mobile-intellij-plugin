package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import java.util.Arrays;
import java.util.List;


public class MobileAPI {
    
    
    private CLIRunner cliRunner;
    
    
    public MobileAPI(CLIRunner cliRunner) {
        this.cliRunner = cliRunner;
    }
    
    public MobileServices getServices() throws CLIException{
        String outPut = cliRunner.executeSync(Arrays.asList("get", "services","--", "-o=json"));
        Gson gson = new Gson();
        try {
            MobileServices services = gson.fromJson(outPut, MobileServices.class);
            return services;
        }catch(JsonSyntaxException e){
            throw new CLIException("unexpected response from CLI: " + outPut);
        }
    }

    

    public void createService(ServiceClass sc, List<String> params, Watcher w) {
        List<String> cmd = Arrays.asList("create","serviceinstance",sc.getServiceName(),"--");
        for (String param : params) {
            cmd.add("-p");
            cmd.add(param);
        }
        cliRunner.executeAsync(cmd, w);
    }


    public MobileClient createClient(String name, String clientType, String bundleID) throws CLIException {
        if (name.isEmpty() || clientType.isEmpty() || bundleID.isEmpty()) {
            throw new CLIException("expected a client name, a client type and a bundle id");
        }
        String res = cliRunner.executeSync(Arrays.asList("create", "client", "--", name, clientType, bundleID, "-o=json"));

        try {
            Gson gson = new Gson();
            MobileClient client = gson.fromJson(res, MobileClient.class);
            return client;
        } catch (JsonSyntaxException e) {
            throw new CLIException("unexpected response from CLI: " + res);
        }
    }

}
