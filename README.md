aerogear-mobile-intellij-plugin
======================


Introduction
-----------

[IntelliJ Platform](http://www.jetbrains.com/idea/) plugin for [Aerogear Mobile services on OpenShift](https://github.com/aerogear/mobile-core).


Installation
------------


Architecture
------------
### IntelliJ Integration
The plugin is integrated into the IntelliJ IDE with a [tool window](http://www.jetbrains.org/intellij/sdk/docs/user_interface_components/tool_windows.html).
See package <code>org.aerogear.plugin.intellij.mobile.ui</code>.

### Mobile cli
The aerogear-mobile-intellij-plugin relies on the [Aerogear mobile-cli](https://github.com/aerogear/mobile-cli) to communicate with your Openshift cluster.



Build (and develop!) the Plugin
------------------

It's very easy to set it up as an IntelliJ project.

1. Download the [Aerogear mobile-cli](https://github.com/aerogear/mobile-cli) and add to path.
2. Activate plugins ```Gradle```, ```Plugin DevKit``` and ```UI Designer``` in IntelliJ.
3. ```git clone https://github.com/aerogear/aerogear-mobile-intellij-plugin.git```
4. Open checked out project in IntelliJ ("File" -> "New" -> "Project from Existing Sources" -> select file ```build.gradle``` in ```aerogear-mobile-intellij-plugin``` folder and press "OK")
5. Create a new run configuration: "Gradle" -> "Gradle project": select the only project -> "Tasks": "runIde"
6. Press "Debug" button. IntelliJ should start with a clean workspace (development sandbox). You need to checkout a
   project to see changes (it shows only changes for Git repositories that are set up in current workspace by default).

Once ```build.gradle``` gets updated, you need to "Refresh all Gradle projects" in the Gradle panel.

Run the plugin tests
1. Ensure that the ```Gradle``` plugin is enabled and properly configured in IntelliJ as detailed above.
2. Open the ```Gradle``` panel and run the task ```test``` under "Tasks" -> "Verification" -> "test"

Contributing
------------
Check the [`CONTRIBUTING.md`](./CONTRIBUTING.md) file.