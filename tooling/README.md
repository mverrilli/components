### Maven
 * In order to build components, you need libraries only found in various repositories that are covered in the root pom.xml. 
 You may need to setup your local .m2/settings.xml if you need to launch integration test that require some credentials such as the salesforce component.  
 

### IntelliJ idea
 * Install [Eclipse Code Formatter](https://plugins.jetbrains.com/plugin/6546) plugin
 * Import the java formatter file [IDEs/eclipse/talend_formatter.xml](/tooling/IDEs/eclipse/talend_formatter.xml)
 * Set the order import manually as followed `java;javax;org;com;`

That's it, you're good to go !

### Eclipse
Import the 3 files found in [/tooling/IDEs/eclipse/](/tooling/IDEs/eclipse/)
