##############################################
To start developing on this project:
##############################################

1- Run command (to create the eclipse project): 
> mvn eclipse:eclipse

2- Run command (to package the project to the target dir):
> mvn install

3- Open Eclipse/STS to and import the project.

4- Select the WAR dir (it should be somewher in /target) when lauching in dev mode.

5- (Optional) Enable Maven dependency.

To deploy in development Mode:

1- Run command (to package the project to the target dir):
> mvn install

2- Run As -> Web Application


NEVER COMMIT "soft.html.launch"

##############################################
TROUBLESHOOTING
##############################################

# SETUP LAUNCH CONFIG FILE
##########################

# We have a common place laucnh configuration (soft.html.launch) which needs to be configured to work properly.

# Here you need to setup the entry point:
<stringAttribute key="org.eclipse.jdt.launching.PROGRAM_ARGUMENTS" value="-server com.google.appengine.tools.development.gwt.AppEngineLauncher -startupUrl soft.html -remoteUI &quot;${gwt_remote_ui_server_port}:${unique_id}&quot; -logLevel INFO -codeServerPort 9997 -port 8888 com.cristal.storm.prototype.soft"/>

# Here you can set the name of your launch configuration
<stringAttribute key="org.eclipse.jdt.launching.PROJECT_ATTR" value="prototype"/>

# Make sure to point to your current appengine-java-sdk jar (which should be located in your maven loca repo).
<stringAttribute key="org.eclipse.jdt.launching.VM_ARGUMENTS" value="-javaagent:C:\Users\JROSE-HP\.m2\repository\com\google\appengine\appengine-java-sdk\1.4.2\appengine-java-sdk-1.4.2\lib\agent\appengine-agent.jar -Xmx512m"/>
