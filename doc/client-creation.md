# Mobile Client Representation

In this document, we will cover what a mobile representation is and how you can create one
from the Android Studio Plugin (ASP). For the purpose of this document, we expect that
you have installed the plugin.


The mobile client representation provides the following things: 
- Gives you a visual representation of your mobile client within Kubernetes or OpenShift. 
- It is used to integrate with existing mobile services such as the 
Aerogear digger (a service for building clients via JenkinsPipelines).
- The details in the mobile client representation are also used as part of the mobile
configuration and are likely to be used as part of the Android SDK.


## Creating the representation

When you start a new project, and you have the ASP installed and enabled, you will see
a popup in the bottom right corner asking if you want to create a new mobile client. 
If you click on the link, a new form will be shown that will ask you for the following
information
- client name (this should be something you recognise)
- applicationIdentifier (this should be set to your package name com.my.company)
- the clientType can remain as android.

When ready simply press ok and that should be it.

A second option for creating the mobile client representation is to go to the:

```tools -> Aerogear Mobile -> create client```

You will then be presented with the same form as before.
