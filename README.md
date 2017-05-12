# Spring-Boot-MVC-WebBasedApplication
Spring Boot application built using maven plugin + dependency Injection +RESTful web service+  jdbc-template +Angular JS

https://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html

https://dzone.com/articles/why-springboot

Spring Framework is used by Java Developers because it has a Dependency Injection tool or Inversion of Control (IoC) container that 
allows writing testable code. Application classes are combined with configuration metadata so that after the "ApplicationContext"(org.
springframework.context.ApplicationContext) is created and initialized, you have a fully configured and executable system or application.

HIgh -level view of Spring Framework :

Business Objects(POJO's) + Configuration Metadata = Spring Container

Spring IoC container consumes a form of configuration metadata (Annotatation Based Configurations such as @Controller @Entity @Service etc
or Java Based Configurations such as  @Configuration ,@Bean ,@Import).Spring IoC container is responsible for instantiating, configuring,
and assembling the aforementioned beans.

Spring Container =>Produces => Ready to Use Fully Configured System to Use.

WHAT ARE BEANS?? IoC Container has one or more beans and Each bean is composed of one or more objects or properties.Bean is generally 
classified using 'id' and 'name' as its identifier(S). The various properties of Beans are:

---- Property-----           ------ Defination-------
class                               INSTANTIATING BEANS
name                                NAMING BEANS
scope                               BEAN SCOPE
constructive arguments              DEPENDENCY INJECTION
properties                          DEPENDENCY INJECTION
autowiring mode                     AUTOWIRING COLLABERATORS

I used Spring Tool Suite(STS) environment which allows boilerplate configuration to  be easily created with few mouse clicks.
Spring Framework allows beans to be configured in 3 ways . 
1.XML.  2.Annotations  3.JavaConfig   ( I will prefer second option here).
