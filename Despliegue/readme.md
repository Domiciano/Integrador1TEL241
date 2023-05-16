# Creación del contenedor de backend
Maven goal
```
mvn clean package
``` 
Se genera el .jar, luego se debe hacer el dockerfile
```
# Utiliza una imagen base de Java
FROM openjdk:11-jdk

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
Puede ejecutar la aplicación usando
```
docker run -p 8080:8080 back:0.0.1
```
```
docker tag back:0.0.1 domi0620/back:0.0.1
```

```
docker push domi0620/back:0.0.1 
```

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
      - my-db:/var/lib/mysql

  backend:
    depends_on:
      - db
    image: domi0620/back:0.0.2  # Nombre y etiqueta de tu imagen del backend
    restart: always  # Opcional: reiniciar el contenedor siempre que se detenga
    ports:
      - '8080:8080'
    expose:
      - '8080'

volumes:
  my-db:

```
# Frontend
```
# Utiliza una imagen base con Nginx instalado
FROM nginx:latest
# Copia el contenido de tu proyecto web al directorio predeterminado de Nginx
COPY . /usr/share/nginx/html
# Exponer el puerto 80 para permitir el acceso a través de HTTP
EXPOSE 80
```

```
docker build -t front:0.0.1 .
```

```
docker tag front:0.0.1 domi0620/front:0.0.1
```

```
docker push domi0620/front:0.0.1          
```

