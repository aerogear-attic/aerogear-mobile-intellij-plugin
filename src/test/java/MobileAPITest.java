import org.aerogear.plugin.intellij.mobile.api.CLIException;
import org.aerogear.plugin.intellij.mobile.api.CLIRunner;
import org.aerogear.plugin.intellij.mobile.api.MobileAPI;
import org.aerogear.plugin.intellij.mobile.models.MobileClient;
import org.aerogear.plugin.intellij.mobile.models.MobileServices;
import org.junit.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class MobileAPITest {
  @Test
  public void testGetServices_ok() throws Exception {
    CLIRunner runner = mock(CLIRunner.class);
    when(runner.executeSync(anyList()  )).thenReturn("{\n"
        + "  \"metadata\": {},\n"
        + "  \"items\": [\n"
        + "    {\n"
        + "      \"metadata\": {\n"
        + "        \"name\": \"1522a4d0e2fbf86a26cbe096eb1b6b2d\",\n"
        + "        \"selfLink\": \"/apis/servicecatalog.k8s.io/v1beta1/clusterserviceclasses/1522a4d0e2fbf86a26cbe096eb1b6b2d\",\n"
        + "        \"uid\": \"2f4ec83b-02a7-11e8-8b00-0242ac110003\",\n"
        + "        \"resourceVersion\": \"58\",\n"
        + "        \"creationTimestamp\": \"2018-01-26T14:42:51Z\"\n"
        + "      },\n"
        + "      \"spec\": {\n"
        + "        \"clusterServiceBrokerName\": \"ansible-service-broker\",\n"
        + "        \"externalName\": \"dh-unifiedpush-apb\",\n"
        + "        \"externalID\": \"1522a4d0e2fbf86a26cbe096eb1b6b2d\",\n"
        + "        \"description\": \"AeroGear UnifiedPush Server\",\n"
        + "        \"bindable\": true,\n"
        + "        \"binding_retrievable\": false,\n"
        + "        \"planUpdatable\": false,\n"
        + "        \"externalMetadata\": {\n"
        + "          \"dependencies\": [\n"
        + "            \"MySQL:55\"\n"
        + "          ],\n"
        + "          \"displayName\": \"AeroGear UPS\",\n"
        + "          \"documentationUrl\": \"https://aerogear.org/push\",\n"
        + "          \"imageUrl\": \"https://pbs.twimg.com/profile_images/1794440005/aerogear_icon-1_400x400.png\",\n"
        + "          \"providerDisplayName\": \"Red Hat, Inc.\",\n"
        + "          \"serviceName\": \"ups\"\n"
        + "        },\n"
        + "        \"tags\": [\n"
        + "          \"mobile-service\"\n"
        + "        ]\n"
        + "      },\n"
        + "      \"status\": {\n"
        + "        \"removedFromBrokerCatalog\": false\n"
        + "      }\n"
        + "    }]}");
    MobileAPI api = new MobileAPI(runner);
    MobileServices services = api.getServices();
    Assert.assertNotNull(services);
    if(services.getItems().length == 0){
      Assert.fail("expected service items but got none");
    }
    
    
  }
  
  @Test(expectedExceptions = CLIException.class)
  public void testGetServices_exception()throws Exception{
    CLIRunner runner = mock(CLIRunner.class);
    when(runner.executeSync(anyList())).thenThrow(new CLIException("failed to execute command"));
    MobileAPI api = new MobileAPI(runner);
    api.getServices();
  }
  
  @Test (expectedExceptions = CLIException.class)
  public void testGetServices_handles_bad_input()throws CLIException{
    CLIRunner runner = mock(CLIRunner.class);
    when(runner.executeSync(anyList())).thenReturn("Error: failed to list service classes: User \"system:anonymous\" cannot list clusterserviceclasses.servicecatalog.k8s.io at the cluster scope: User \"system:anonymous\" cannot list all clusterserviceclasses.servicecatalog.k8s.io in the cluster (get clusterserviceclasses.servicecatalog.k8s.io)\n"
        + "error: exit status 1");
    MobileAPI api = new MobileAPI(runner);
    api.getServices();
  }
  
  
  @Test
  public void testCreateClient_ok()throws CLIException{
    CLIRunner runner = mock(CLIRunner.class);
    MobileAPI api = new MobileAPI(runner);
    when(runner.executeSync(anyList())).thenReturn("{\n"
        + "\t\"kind\": \"MobileClient\",\n"
        + "\t\"apiVersion\": \"mobile.k8s.io/v1alpha1\",\n"
        + "\t\"metadata\": {\n"
        + "\t\t\"name\": \"mine-ios\",\n"
        + "\t\t\"namespace\": \"myproject\",\n"
        + "\t\t\"selfLink\": \"/apis/mobile.k8s.io/v1alpha1/namespaces/myproject/mobileclients/mine-ios\",\n"
        + "\t\t\"uid\": \"4603321c-050e-11e8-84ba-e29d276b20c6\",\n"
        + "\t\t\"resourceVersion\": \"124378\",\n"
        + "\t\t\"creationTimestamp\": \"2018-01-29T16:05:49Z\",\n"
        + "\t\t\"labels\": {\n"
        + "\t\t\t\"icon\": \"fa-apple\"\n"
        + "\t\t}\n"
        + "\t},\n"
        + "\t\"spec\": {\n"
        + "\t\t\"name\": \"mine\",\n"
        + "\t\t\"apiKey\": \"394be8dc-c2d2-45eb-b455-4441fea9f5e4\",\n"
        + "\t\t\"clientType\": \"iOS\",\n"
        + "\t\t\"appIdentifier\": \"com.my.company\"\n"
        + "\t}\n"
        + "}");
    MobileClient client = api.createClient("mine","iOS","com.my.company");
    Assert.assertNotNull(client);
    Assert.assertNotNull(client.spec);
    Assert.assertEquals(client.spec.name,"mine");
    Assert.assertEquals(client.spec.appIdentifier,"com.my.company");
    Assert.assertEquals(client.spec.clientType,"iOS");
    Assert.assertNotEquals("",client.spec.apiKey);
    
  }
  
  @Test(expectedExceptions = CLIException.class)
  public void testCreateClient_fails_validation_bundleID()throws CLIException{
    CLIRunner runner = mock(CLIRunner.class);
    MobileAPI api = new MobileAPI(runner);
    api.createClient("mine","android","");
  }
  
  
}
