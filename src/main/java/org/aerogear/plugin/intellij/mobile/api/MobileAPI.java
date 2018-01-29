package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MobileAPI {
    
    
    private CLIRunner cliRunner;

    public MobileAPI(CLIRunner runner) {
        this.cliRunner = runner;
    }

    
    
    
    public MobileServices getServices() throws CLIException{
        
        List<String> cmd = cliRunner.prepareCmd("get", "services","--", "-o=json");
        String outPut = cliRunner.runCmd(cmd,5);
        Gson gson = new Gson();
        
        MobileServices services = gson.fromJson(outPut, MobileServices.class);
        return services;
    }

    

    public void createService(ServiceClass sc, List<String> params, Watch w) {
        List<String> cmd = cliRunner.prepareCmd("create","serviceinstance",sc.getServiceName(),"--");
        
        for (String param : params) {
            cmd.add("-p");
            cmd.add(param);
        }
        
        cliRunner.runAndWatch(cmd, w);
    }
    
    
    public String createClient(String name, String clientType, String bundleID)throws CLIException{
        if("".equals(name) || "".equals(clientType) || "".equals(bundleID)){
            throw new CLIException("expected a client name, a client type and a bundle id");;
        }
        List<String> cmd = cliRunner.prepareCmd("create","client",name,clientType,bundleID);
        return cliRunner.runCmd(cmd,5);
    }
    
}
