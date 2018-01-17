package org.aerogear.plugin.intellij.mobile.ui.cmd.services;

public class Spec
{
    private ExternalMetadata externalMetadata;

    private String externalName;

    private String description;

    private String externalID;

    private String clusterServiceBrokerName;

    public ExternalMetadata getExternalMetadata ()
    {
        return externalMetadata;
    }

    public void setExternalMetadata (ExternalMetadata externalMetadata)
    {
        this.externalMetadata = externalMetadata;
    }

    public String getExternalName ()
    {
        return externalName;
    }

    public void setExternalName (String externalName)
    {
        this.externalName = externalName;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getExternalID ()
    {
        return externalID;
    }

    public void setExternalID (String externalID)
    {
        this.externalID = externalID;
    }

    public String getClusterServiceBrokerName ()
    {
        return clusterServiceBrokerName;
    }

    public void setClusterServiceBrokerName (String clusterServiceBrokerName)
    {
        this.clusterServiceBrokerName = clusterServiceBrokerName;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [externalMetadata = "+externalMetadata+", externalName = "+externalName+", description = "+description+", externalID = "+externalID+", clusterServiceBrokerName = "+clusterServiceBrokerName+"]";
    }
}
