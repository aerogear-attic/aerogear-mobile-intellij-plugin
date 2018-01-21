package org.aerogear.plugin.intellij.mobile.models;

public class ExternalMetadata
{
    private String documentationUrl;

    private String imageUrl;

    private String displayName;

    private String providerDisplayName;

    private String serviceName;

    public String[] getDependencies() {
        return dependencies;
    }

    private String[] dependencies;

    public String getDocumentationUrl ()
    {
        return documentationUrl;
    }

    public void setDocumentationUrl (String documentationUrl)
    {
        this.documentationUrl = documentationUrl;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getDisplayName ()
    {
        return displayName;
    }

    public void setDisplayName (String displayName)
    {
        this.displayName = displayName;
    }

    public String getProviderDisplayName ()
    {
        return providerDisplayName;
    }

    public void setProviderDisplayName (String providerDisplayName)
    {
        this.providerDisplayName = providerDisplayName;
    }

    public String getServiceName ()
    {
        return serviceName;
    }

    public void setServiceName (String serviceName)
    {
        this.serviceName = serviceName;
    }

    @Override
    public String toString()
    {
        return "documentationUrl=" + documentationUrl+", imageUrl = " + imageUrl + ", displayName = " + displayName + ", providerDisplayName = " + providerDisplayName + ", serviceName = " + serviceName;
    }
}
