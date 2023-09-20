# Implementación en Springboot

Dependencias necesarias para los datos
```
<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
   <version>8.0.26</version>
</dependency>

<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

## Propiedades
Agregue a src/main/resources un archivo llamado application.properties

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://200.3.193.22:3306/DATABASE
spring.datasource.username=USER
spring.datasource.password=PASS
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.tomcat.max-active=2
```



