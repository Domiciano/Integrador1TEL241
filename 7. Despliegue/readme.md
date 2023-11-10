# Creación del contenedor de backend

## 1. Preparación
Este comando le permitirá generar el JAR para ejecutar la aplicación
```
mvn clean package -DskipTests
``` 
Se genera el .jar, luego se debe hacer el dockerfile


## 2. Generación de imagen

```
# Utiliza una imagen base de Java
FROM openjdk:20-jdk

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia el archivo JAR del backend al directorio de trabajo
COPY ./target/Banner.jar /app/backend.jar

# Expone el puerto en el que se ejecutará el backend (ajusta el número de puerto según tus necesidades)
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "backend.jar"]

```
Luego se hace el build de la imagen de docker
```
docker build -t back:0.0.1 .
```
Todas las versiones vienen con un nombre de imagen y un número de versión. Se puede usar un formato de tres puntos de versión.</br></br>

## 3. Ejecutar imagen
Para ejecutar la imagen en un contenedor use
```
docker run -p 8080:8080 back:0.0.1
```
Esto generará un contenedor con nombre aleatorio que se ejecutará en el puerto 8080 y que está mapeado al puerto 8080 del contenedor.</br></br>

## 4. Publicación
Querrá usar su imagen de docker, para hacerlo debe subir su imagen a DockerHUB. Para iniciar el proceso use un tag
```
docker tag back:0.0.1 domi0620/back:0.0.1
```
En el tag especifique el nombre de su imagen local y luego un nombre cuyo prefijo sea su nombre de usuario en dockerhub.</br></br>
Ahora puede hacer push del stack para tener un backup online de su imagen. Esto le permitirá usar las imágenes y construir un stack de servicios
```
docker push domi0620/back:0.0.1 
```

## 5. Usar variables de entorno
Puede ser conveniente usar variables de entorno en los proyecto para evitar que se realicen builds consecutivos.
Por ejemplo, en el application.properties del backend puede usar variables de entorno
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.username=user
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.tomcat.max-active=2
spring.datasource.url=${DATA_SOURCE_URL}
server.servlet.context-path=${APP_PATH}
```

El llamado ${MI_VARIABLE} lo hace directamente el runtime y permite parametrizar configuraciones o constantes en los programas

## 6. Primer Docker Compose
En este docker compose sólo se va a hacer el montaje de la base de datos y del Rest API

```
version: "3.7"
services:
  bannerdb:
    command: ["--max_connections=1000"]
    image: mysql:5.7
    restart: always
    environment:
      MYSQL_DATABASE: 'db'
      MYSQL_USER: 'user'
      MYSQL_PASSWORD: 'password'
      MYSQL_ROOT_PASSWORD: 'password'
    volumes:
      - bannerdata:/var/lib/mysql
    ports:
      - '3306:3306'
    networks:
      - mired

  bannerbackend:
    depends_on:
      - bannerdb
    image: domi0620/back:0.0.12
    restart: always
    ports:
      - '8080:8080'
    expose:
      - '8080'
    environment:
      - DATA_SOURCE_URL=jdbc:mysql://bannerdb:3306/db
      - APP_PATH=/bannerapi
    networks:
      - mired

volumes:
  bannerdata:
  
networks:
  mired:
```
Para inciar la ejecución use
```
docker-compose up -d
```

Para detener su stack
```
docker-compose down
```

```
docker network ls
```

```
docker network inspect <NETWORK ID>
```



## 7. Desplegar a producción
Para subir en un orquestador de contenedores como portainer se debe usar la configuración establecida por el administrador para poder hacer la publicación.

```
version: "3.7"
services:
  bannerdb:
    command: ["--max_connections=1000"]
    image: mysql:5.7
    restart: always
    environment:
      MYSQL_DATABASE: 'db'
      MYSQL_USER: 'user'
      MYSQL_PASSWORD: 'password'
      MYSQL_ROOT_PASSWORD: 'password'
    volumes:
      - bannerdata:/var/lib/mysql
    networks: # Se usa una red llamada proxy configurado en el portainer para que tenga salida a internet
      - proxy

  bannerbackend:
    depends_on:
      - bannerdb
    image: domi0620/back:0.0.12
    restart: always
    ports:
      - '8081:8080'
    expose:
      - '8081'
    environment:
      - DATA_SOURCE_URL=jdbc:mysql://bannerdb:3306/db
      - APP_PATH=/bannerapi
    networks: # Se usa una red llamada proxy configurado en el portainer para que tenga salida a internet
      - proxy
    deploy: # Se la propiedad deploy para establecer el número de réplicas de la imagen y se configura el path
        replicas: 1
        labels: 
          com.df.distribute: "false"
          com.df.notify: "true"
          com.df.port: 8080
          com.df.servicePath: "/bannerapi"

volumes:
  bannerdata:
    external: true # Se configura el volumen como externa

networks:
  proxy:
    external: true # Se configura la red como externa
```







# Frontend

## 1. Preparación
Configuración del servidor nginx. Corresponde al archivo nginx.conf
```
server {
    listen 80;
    location /banner {
        alias /usr/share/nginx/html;
        index index.html;
        try_files $uri $uri/ /banner/index.html;
    }
}
```

## 2. Generación de imagen

```
# Utiliza una imagen base con Nginx instalado
FROM --platform=linux/amd64 nginx:latest
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY . /usr/share/nginx/html/
# Exponer el puerto 80 para permitir el acceso a través de HTTP
EXPOSE 80
```

```
docker build -t front:0.0.1 .
```

## 3. Ejecutar imagen
Para ejecutar la imagen en un contenedor use
```
docker run -p 80:80 front:0.0.1
```
Esto generará un contenedor con nombre aleatorio que se ejecutará en el puerto 8080 y que está mapeado al puerto 8080 del contenedor.</br></br>

## 4. Publicación
Querrá usar su imagen de docker, para hacerlo debe subir su imagen a DockerHUB. Para iniciar el proceso use un tag
```
docker tag front:0.0.1 domi0620/front:0.0.1
```
En el tag especifique el nombre de su imagen local y luego un nombre cuyo prefijo sea su nombre de usuario en dockerhub.</br></br>

Ahora puede hacer push del stack para tener un backup online de su imagen. Esto le permitirá usar las imágenes y construir un stack de servicios
```
docker push domi0620/front:0.0.1          
```



## Próximamente



```
spring.datasource.url=jdbc:mysql://db:3306/db
server.servlet.context-path=/bannerapi
```

```
https://i2thub.icesi.edu.co:5443/bannerapi/echo
```

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.username=user
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.tomcat.max-active=2
spring.datasource.url=${DATA_SOURCE_URL}
server.servlet.context-path=${APP_PATH}
```


