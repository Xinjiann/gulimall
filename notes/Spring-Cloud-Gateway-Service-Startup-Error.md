# Spring Cloud Gateway service startup error


# Error 1

```java
***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of method hystrixGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration$HystrixConfiguration required a bean of type 'org.springframework.web.reactive.DispatcherHandler' that could not be found.


Action:

Consider defining a bean of type 'org.springframework.web.reactive.DispatcherHandler' in your configuration.


Process finished with exit code 1
```


## Solution 1

Since the internal of springclougateway is realized through netty + webflux, and webflux conflicts with springmvc, so web from parent project needs to be removed.

```java
<dependency>
　　<groupId>com.fdzang.microservice</groupId>
　　<artifactId>api-common</artifactId>
　　<version>1.0-SNAPSHOT</version>
　　<exclusions>
　　　　<exclusion>
　　　　　　<groupId>org.springframework.boot</groupId>
　　　　　　<artifactId>spring-boot-starter-web</artifactId>
　　　　</exclusion>
　　</exclusions>
</dependency>
```
## Solution 2

```java
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
    <exclusions>
        <exclusion>
            <artifactId>jsr305</artifactId>
            <groupId>com.google.code.findbugs</groupId>
        </exclusion>
    </exclusions>
</dependency>
```
fixed.

# Error 2
```java
***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class
```
## Solution

Since Mybatis was imported in the parent project, datasource was automatically generated, so its necessary to exclude datasource class.


A much easier way it to add the following annotations to GatewayApplication.

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguratgion.class)
```

