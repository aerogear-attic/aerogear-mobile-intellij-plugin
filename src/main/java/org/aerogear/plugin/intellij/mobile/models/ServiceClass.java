package org.aerogear.plugin.intellij.mobile.models;

public class ServiceClass
{
    private ClusterServiceSpec spec;

    public ClusterServiceSpec getSpec ()
    {
        return spec;
    }

    public void setSpec (ClusterServiceSpec spec)
    {
        this.spec = spec;
    }

    public String getDisplayName() {
        return getSpec().getExternalMetadata().getDisplayName();
    }

    public String getServiceName() {
        return getSpec().getExternalMetadata().getServiceName();
    }

    public String getExternalName() {
        return getSpec().getExternalName();
    }

    public String getDescription() {
        return getSpec().getDescription();
    }
    @Override
    public String toString()
    {
        return "spec=" + spec;
    }
}

