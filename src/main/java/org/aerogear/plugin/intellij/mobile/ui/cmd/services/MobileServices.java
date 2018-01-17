package org.aerogear.plugin.intellij.mobile.ui.cmd.services;

public class MobileServices
{
    private ServiceClass[] items;

    public ServiceClass[] getItems ()
    {
        return items;
    }

    public void setItems (ServiceClass[] items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [items = "+items+"]";
    }
}
