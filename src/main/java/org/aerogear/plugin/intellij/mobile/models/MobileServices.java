package org.aerogear.plugin.intellij.mobile.models;

import java.util.Arrays;

public class MobileServices
{
    private ServiceClass[] items = new ServiceClass[0];

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
        return "items = " + Arrays.toString(items);
    }
}
