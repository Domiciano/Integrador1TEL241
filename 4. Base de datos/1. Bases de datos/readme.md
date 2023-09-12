## Base de datos de la universidad

Ingresa a este enlace para ir a la base de datos de la universidad
https://200.3.193.22/

<!--
https://200.3.193.22/
P09728_1_11
ZCSaQGZU
-->

## Datos
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


## Problemas de modelamiento

```
Sistema de ventas de una tienda de juguetes
```

```
Sistema de reservas de una aerolínea
```

```
Sistema de biblioteca ICESI
```

```
Sistema de registro para actividades de Bienestar Universitario
```

```
Sistema para reclamación de almuerzos para becados ICESI
```
