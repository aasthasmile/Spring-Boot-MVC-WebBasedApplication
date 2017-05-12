# Spring-Boot-MVC-WebBasedApplication
Spring Boot application built using maven plugin + dependency Injection +RESTful web service+  jdbc-template +Angular JS

https://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html

https://dzone.com/articles/why-springboot

Spring Framework is used by Java Developers because it has a Dependency Injection tool or Inversion of Control (IoC) container that 
allows writing testable code. Application classes are combined with configuration metadata so that after the "ApplicationContext"(org.
springframework.context.ApplicationContext) is created and initialized, you have a fully configured and executable system or application.

High -level view of Spring Framework :

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
1.XML based configurations  2.Annotations based confogurations  3.JavaConfig   ( I will prefer second option here).

XML based configurations:
<bean id="userService" class="com.sivalabs.myapp.service.UserService">
    <property name="userDao" ref="userDao"/>
</bean>

<bean id="userDao" class="com.sivalabs.myapp.dao.JdbcUserDao">
    <property name="dataSource" ref="dataSource"/>
</bean>

<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/test"/>
    <property name="username" value="root"/>
    <property name="password" value="secret"/>
</bean>

Annotations based confogurations:

@Service
public class UserService
{
   private UserDao userDao;

    @Autowired
    public UserService(UserDao dao){
        this.userDao = dao;
    }
 } 
 
 JavaConfig :
 
@Configuration
public class AppConfig
{
    @Bean
    public UserService userService(UserDao dao){
        return new UserService(dao);
    }
    @Bean
    public UserDao userDao(DataSource dataSource){
        return new JdbcUserDao(dataSource);
    }

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("secret");
        return dataSource;
    }

}


STEPS FOR BUILDING SPRING MVC APPLICATION 

STEP-1 
CONFIGURE MAVEN DEPENDENCIES = > pom.xml
Add all the dependencies that are required to make a Spring-boot-starter application.

<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>4.2.4.RELEASE</version>
</dependency>


In these dependencies , we will use spring-boot-starter-* dependencies.Springboot-starter-web dependency( by default), it will pull all the commonly used libraries while developing Spring MVC applications such as spring-webmvc, jackson-json, validation-api and tomcat.
Similarly spring-boot-starter-data-jpa dependency provide database related dependencies .

Embedded Servlet Container Support:Where is the servlet container comes from? 
We have added spring-boot-starter-web which pulls the spring-boot-starter-tomcat automatically and when we run the main() method it started tomcat as an embedded container so that we don’t have to deploy our application on any externally installed tomcat server.
Packaging type in pom.xml is ‘jar’ not ‘war’.

STEP-2
CREATE A MODEL CLASS 

@Entity 
class className{

@Id //Primary Key
@ColumnName(name="ColumnNametobeSpecifiedHere")
@GeneratedType() //AutoGenerate ineteger id 
etcs
private int id;
}

STEP-3 
CREATE A REPOSITORY CLASS ( IT has all the jdbc-template CREATE ,READ ,UPDATE ,DELETE operations)
Spring JdbcTemplate class instances are thread-safe.Spring JdbcTemplate provides a huge support of exception handling with the more explanatory manner; that is the JdbcTemplate converts the standard Jdbc exceptions to more explanatory manner to the developers. Those are defined in the org.springframework.dao package. (dao means data access objects).

@Repository
public class UserRepository implements UserDAL{

	@Autowired
	public JdbcTemplate jdbcTemplate;
        
        public List<User> getAllUser(){}
	public int CreateUser(User user){}
	public int updateUser(User user){}
	public int deleteUser(String id){}
	public void insertBatch(){}
}
STEP-4
CREATE A SERVICE CLASS

STEP-5
CREATE A CONTROLLER CLASS (@RestController @RequestMapping @RequestBody annotations and HTTP Reuest Methods - GET ,POST,PUT,DELETE)

@RequestMapping(value="/",method = RequestMethod.GET)
	 public void home(HttpServletResponse response) throws IOException { 
                response.sendRedirect("app/index.html");     }
                
@RequestMapping(value = "users", method = RequestMethod.GET, produces =MediaType.APPLICATION_JSON_VALUE)
                public List<User> getUsersInfo() throws Exception {}

@RequestMapping(value = "/users/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	        public ResponseObject createUser(@RequestBody User user) throws Exception {}

@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	        public ResponseObject updateUser(@PathVariable("id") long id, @RequestBody User user) throws Exception {}
			
@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject deleteUser(@PathVariable("id") long id) throws Exception {}
        
        
