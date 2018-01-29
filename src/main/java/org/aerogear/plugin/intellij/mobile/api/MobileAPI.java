package org.aerogear.plugin.intellij.mobile.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.aerogear.plugin.intellij.mobile.models.ServiceClass;

import java.util.List;


public class MobileAPI {
    
    
    private CLIRunner cliRunner;
    
    public MobileAPI(){}
    
    
    public MobileAPI(CLIRunner cliRunner) {
        this.cliRunner = cliRunner;
    }
    
    public MobileServices getServices() throws CLIException{
        
        List<String> cmd = cliRunner.prepareCmd("get", "services","--", "-o=json");
        String outPut = cliRunner.runCmd(cmd,5);
        Gson gson = new Gson();
        try {
            MobileServices services = gson.fromJson(outPut, MobileServices.class);
            return services;
        }catch(JsonSyntaxException e){
            throw new CLIException("unexpected response from CLI: " + outPut);
        }
    }

    

    public void createService(ServiceClass sc, List<String> params, Watch w) {
        List<String> cmd = cliRunner.prepareCmd("create","serviceinstance",sc.getServiceName(),"--");
        
        for (String param : params) {
            cmd.add("-p");
            cmd.add(param);
        }
        
        cliRunner.runAndWatch(cmd, w);
    }
    
    
    public MobileClient createClient(String name, String clientType, String bundleID)throws CLIException{
        if("".equals(name) || "".equals(clientType) || "".equals(bundleID)){
            throw new CLIException("expected a client name, a client type and a bundle id");
        }
        List<String> cmd = cliRunner.prepareCmd("create","client",name,clientType,bundleID);
        String res = cliRunner.runCmd(cmd,5);
        
        try{
            Gson gson = new Gson();
            MobileClient client =  gson.fromJson(res,MobileClient.class);
            return client;
        }catch (JsonSyntaxException e) {
            throw new CLIException("unexpected response from CLI: " + res);
        }
    }
    
}
