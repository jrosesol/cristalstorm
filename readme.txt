To start developing on this project:

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

