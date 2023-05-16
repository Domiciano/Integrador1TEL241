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
