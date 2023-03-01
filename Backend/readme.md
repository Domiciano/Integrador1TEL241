### Agregue el padre al proyecto Maven


Esto permite hacer que podamos generar un API deployable
```
<packaging>war</packaging>
```


Esto permite agregar las dependencias sin necesidad de señalar una versión
```
  <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.3</version>
        <relativePath/> 
    </parent>
```


```
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
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


    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

        </plugins>


    </build>
```

Método main de nuestro repo
```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
```


Liste los programas corriendo sobre un puerto<br><br>
Unix
```
lsof -i:8080
```
Windows
```
netstat -ano | findstr :<PORT>
```

Mate el proceso en el puerto elegido<br><br>
Unix
```
kill $(lsof -t -i:8080)
```
Windows
```
taskkill /PID <PID> /F
```
