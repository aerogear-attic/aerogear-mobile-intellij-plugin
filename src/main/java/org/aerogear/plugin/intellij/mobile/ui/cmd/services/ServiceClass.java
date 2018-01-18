package org.aerogear.plugin.intellij.mobile.ui.cmd.services;

public class ServiceClass
{
    private Spec spec;

    public Spec getSpec ()
    {
        return spec;
    }

    public void setSpec (Spec spec)
    {
        this.spec = spec;
    }

    @Override
    public String toString()
    {
        return "" + spec;
    }
}

