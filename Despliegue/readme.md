# Creación del contenedor de backend

## 1. Preparación
Este comando le permitirá generar el JAR para ejecutar la aplicación
```
mvn clean package
``` 
Se genera el .jar, luego se debe hacer el dockerfile


## 2. Generación de imagen

```
# Utiliza una imagen base de Java
FROM --platform=linux/amd64 openjdk:11-jdk

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
services:
  db:
    platform: linux/x86_64
    command: ["--max_connections=1000"]
    image: mysql:5.7
    restart: always
    environment:
      MYSQL_DATABASE: 'db'
      MYSQL_USER: 'user'
      MYSQL_PASSWORD: 'password'
      MYSQL_ROOT_PASSWORD: 'password'
    ports:
      - '3306:3306'
    expose:
      - '3306'
    volumes:
      - dbstorage:/var/lib/mysql

  backend:
    depends_on:
      - db
    image: domi0620/back:0.0.4  # Nombre y etiqueta de tu imagen del backend
    restart: always  # Opcional: reiniciar el contenedor siempre que se detenga
    ports:
      - '8080:8080'
    expose:
      - '8080'

  frontend:
    depends_on:
      - backend
    image: domi0620/front:0.0.6  # Nombre y etiqueta de tu imagen del backend
    restart: always  # Opcional: reiniciar el contenedor siempre que se detenga
    ports:
      - '80:80'
    expose:
      - '80'

volumes:
  dbstorage:

```


```
spring.datasource.url=jdbc:mysql://db:3306/db
server.servlet.context-path=/banner/api
```



