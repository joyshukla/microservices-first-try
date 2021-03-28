# microservices-demo

Demo application to go with my [Microservices Blog](https://spring.io/blog/2015/07/14/microservices-with-spring) on the spring.io website.  **WARNING:** Only maven build has been updated.  Gradle build still to be done.

![Demo System Schematic](https://github.com/paulc4/microservices-demo/blob/master/mini-system.jpg)

Clone it and either load into your favorite IDE or use maven/gradle directly.

You can run the system in your IDE by running the three server classes in order:
_RegistrationService_,
_AccountsService_
_WebService_
_PostService_
_ForumService_

Each is a Spring Boot application using embedded Tomcat.  If using Spring Tools use `Run As ... Spring Boot App` otherwise just run each as a Java application - each has a static `main()` entry point.

As discussed in the Blog, open the Eureka dashboard [http://localhost:1111](http://localhost:1111) in your browser to see that the `ACCOUNTS-SERVICE`, `WEB-SERVICE`, 'POST-SERVICE' AND 'FORUM-SERVICE' applications have registered.
Next open the Forum Demo Home Page [http://localhost:5555](http://localhost:5555) in and click one of the demo links.


The `localhost:3333` web-site is being handled by a Spring MVC Controller in the _WebService_ application, but you should also see logging output from _AccountsService_ showing requests for Account data.

