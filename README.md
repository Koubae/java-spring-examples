Java Spring Examples
==================== 


Collection of Java Spring App examples, good for learning and collect re-usable code snippets or concepts

See Also : [Java - Koubae/Programming-CookBook](https://github.com/Koubae/Programming-CookBook/tree/master/Programming%20Languages/Java)


Apps
----



Documentation
-------------

### Spring 

* [What exactly is Field Injection and how to avoid it?](https://stackoverflow.com/a/39892204/13903942)
    * [Field Dependency Injection Considered Harmful - Blog](https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/)

* **6.0.9**
    * [The IoC Container](https://docs.spring.io/spring-framework/reference/core/beans.html)
    * [Constructor-based Dependency Injection](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/dependencies/factory-collaborators.html#beans-constructor-injection)
    * [Setter-based Dependency Injection](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/dependencies/factory-collaborators.html#beans-setter-injection)

* **4.2**
    * [The IoC container](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/beans.html) 
    * [Constructor-based dependency injection](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/beans.html#beans-constructor-injection) 
    * [Setter-based dependency injection](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/beans.html#beans-setter-injection) 


Frameworks & Libraries
--------


### Testing

#### Integration Tests

* [Rest Assured](https://rest-assured.io/)


Projects
--------


### Spring 

* [Are there any big spring-boot open source projects?](https://stackoverflow.com/questions/54782469/are-there-any-big-spring-boot-open-source-projects)
<div class="s-prose js-post-body" itemprop="text" style="display: block;">
<p>Here are some non pet store but a real world , non trivial , and open source application that use Spring Boot 2.</p>
<ol>
<li><p><a href="https://thingsboard.io/" rel="nofollow noreferrer">Thingsboard</a> which is an IoT platform with the microservice architecture. Built with Spring Boot , Spring Security , Spring Data , Spring MVC etc.</p>
</li>
<li><p><a href="http://www.flowable.org" rel="nofollow noreferrer">Flowable</a> is a business process engines that are based on Spring and have already upgrade to <a href="https://blog.flowable.org/2018/04/11/the-road-to-spring-boot-2-0/" rel="nofollow noreferrer">support Spring Boot 2.0</a></p>
</li>
<li><p><a href="https://github.com/spring-io/initializr" rel="nofollow noreferrer">Spring Initializr</a> is the backend web API that can quickly generate a sample spring-boot project. It is exactly the backend API that powered the famous <a href="https://start.spring.io/" rel="nofollow noreferrer">start.spring.io</a>.</p>
</li>
<li><p><a href="https://github.com/obsidiandynamics/kafdrop" rel="nofollow noreferrer">Kafdrop</a> is the web client that managing Kafka. Built with Spring Boot, Spring MVC, Freemarker etc.</p>
</li>
<li><p><a href="https://www.klaw-project.io/" rel="nofollow noreferrer">Klaw</a> is another web client that managing Kafka. Built with Spring Boot, Spring MVC, Spring Security,Spring Data JPA and Thymeleaf etc.</p>
</li>
<li><p>The <a href="https://github.com/corona-warn-app/cwa-server" rel="nofollow noreferrer">backend</a> of the <a href="https://www.coronawarn.app/en/" rel="nofollow noreferrer">Corona-Warn-App</a> which is an app that helps trace infection chains of COVID-19 in Germany.Built with Spring Boot, Spring MVC, Spring Security,Spring Data JPA, Bean Validation etc.</p>
</li>
<li><p>CloudFoundry User Account and Authentication (<a href="https://github.com/cloudfoundry/uaa" rel="nofollow noreferrer">UAA</a>) Server is a multi tenant identity management service used in Cloud Foundry , but also available as a stand alone OAuth2 server. Built with Spring Boot , Spring MVC , Spring Security , Spring JDBC etc.</p>
</li>
<li><p><a href="https://github.com/apolloconfig" rel="nofollow noreferrer">Apollo</a> is the distributed configuration management system to allow you change your application 's configuration to take effect in the real time without starting your application. Can be used to implement grayscale release too. Built with Spring Boot , Spring Cloud (e.g Eureka) , Spring Session , Spring Security , Spring MVC , Spring Data JPA etc.</p>
</li>
</ol>
    </div>


Further Readins
---------------


* ["Loading class com.mysql.jdbc.Driver ... is deprecated" message](https://stackoverflow.com/questions/52344453/loading-class-com-mysql-jdbc-driver-is-deprecated-message)
* [Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver' [duplicate]](https://stackoverflow.com/questions/52032739/loading-class-com-mysql-jdbc-driver-this-is-deprecated-the-new-driver-class)
* [Why it is necessary to extend`SpringBootServletInitializer` while deploying it to an external tomcat](https://stackoverflow.com/questions/48047909/why-it-is-necessary-to-extendspringbootservletinitializer-while-deploying-it-t)