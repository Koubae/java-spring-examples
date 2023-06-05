Java - Spring Todo List app 
===========================

_Simple Todo List app written in Java using Spring & Spring Boot_

- [How to reduce spring boot memory usage?](https://stackoverflow.com/questions/44491257/how-to-reduce-spring-boot-memory-usage)
- [Configuring JVM options and platform properties ](https://intellij-support.jetbrains.com/hc/en-us/articles/206544869-Configuring-JVM-options-and-platform-properties)

In order to, lower spring app resource eating set these JVM Options:

```bash 
With -XX:+UseSerialGC This will perform garbage collection inline with the thread allocating the heap memory instead of a dedicated GC thread(s)

With -Xss512k This will limit each threads stack memory to 512KB instead of the default 1MB

With -XX:MaxRAM=72m This will restrict the JVM's calculations for the heap and non heap managed memory to be within the limits of this value.

In addition to the above JVM options you can also use the following property inside your application.properties file:

server.tomcat.max-threads = 1 This will limit the number of HTTP request handler threads to 1 (default is 200)


```

```yaml 
-XX:+UseSerialGC
-Xss512k
-XX:MaxRAM=72m

```


**Example for Intellij **
```yaml 

-server
-Xms128m
-Xmx512m
-XX:ReservedCodeCacheSize=240m
-XX:+UseConcMarkSweepGC
-XX:SoftRefLRUPolicyMSPerMB=50
-ea
-Dsun.io.useCanonCaches=false
-Djava.net.preferIPv4Stack=true
-XX:+HeapDumpOnOutOfMemoryError
-XX:-OmitStackTraceInFastThrow
-XX:MaxJavaStackTraceDepth=-1
```