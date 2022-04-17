# SportLand
## Participación y creación de eventos deportivos gratuitos (Java Spring Boot)
### Desarrollado con:
* Maven
* Java 8 (JDK 1.8.131)
* Spring Boot
* JSP
* HTML5
* CSS3
* Bootstrap
* MySQL
* Bcrypt
### Esctructura de la base de datos (Modelo visual del esquema, el cual se genera automáticamente una vez creada el nombre de la base de datos y los modelos en nuestra aplicación)
![alt ERD_diagram](https://github.com/HenryCodeT/iSport-Java_Spring/blob/main/assets/diagrama_ERD_SportLand.png)
### Dependencias
```xml
        <!-- DEPENDENCIES FOR STARTING SPRING PROJECTS -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- DEPENDENCIES FOR INTEGRATING SQL DATABASE AND USING JPA -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!-- DEPENDENCIES FOR DISPLAYING JSPS AND USING JSTL TAGS -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
        <!-- DEPENDENCY FOR USING VALIDATION ANNOTATIONS -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <!-- DEPENDENCIES FOR BOOTSTRAP -->
        <!-- locator -->
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>webjars-locator</artifactId>
            <version>0.30</version>
        </dependency>
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>jquery</artifactId>
            <version>3.6.0</version>
        </dependency>
        <!-- bootstrap -->
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>bootstrap</artifactId>
            <version>5.1.3</version>
        </dependency>
        <!-- DEPENDENCY FOR USING BCRYPT -->
        <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
```
### Aplication Properties
``` 
# MySQL Access
# ** Create the schema in mysql ** 
spring.datasource.url=jdbc:mysql://localhost:3306/spring_schema
# Your MySQL Username
spring.datasource.username=root
# Your MySql Password
spring.datasource.password=admin
# Your Local Time Zone
spring.jackson.time-zone=GMT-5
# Spring JPA
spring.jpa.hibernate.ddl-auto=update
# .JSP & Folder
spring.mvc.view.suffix = .jsp
spring.mvc.view.prefix = /WEB-INF/
# Hidden Method (PUT & DELETE)
spring.mvc.hiddenmethod.filter.enabled=true
```
