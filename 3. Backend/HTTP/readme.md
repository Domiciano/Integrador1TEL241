### Taller


<a href="https://docs.google.com/document/d/1eVsIclSsa5QXd2UZXWuFBEk5azrAltcGX1i1MuTqIJk/edit?usp=sharing">Taller REST</a>



### Agregue el padre al proyecto Maven


Esto permite hacer que podamos generar un API deployable
```
<packaging>jar</packaging>
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

Estas son las dependencias para poder armar un REST API
```
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>


    </dependencies>

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

Ya con todas las condiciones iniciales puede agregar un controller
```
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        return "Integrador 1";
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
