<img width="256" src="https://www.icesi.edu.co/launiversidad/images/La_universidad/logo_icesi.png">

# Protocolo MQTT
Para crear un servidor MQTT simple cree una imagen de docker usando la siguiente estructura de carpetas:


```
* images
   * mosquitto
      - mosquitto.conf
      - Dockerfile  
* composes
   * mosquitto
      - docker-compose.yml
```
Convención
```
* Carpeta
- Archivo
```

En el archivo Dockerfile use esta plantilla

```
FROM eclipse-mosquitto:2

# Copiar el archivo de configuración personalizado
COPY mosquitto.conf ./mosquitto/config/mosquitto.conf

# Exponer los puertos
EXPOSE 1883 8883 9001
```

Y en el archivo mosquitto.conf escriba lo siguiente
```
allow_anonymous true

listener 1883
protocol mqtt

listener 9001
protocol websockets
```

Lo que está haciendo es construyendo su propia imagen del MQTT server con su propio archivo de configuración

## Creación de la imagen
Para crear la imagen debe estar ubicado en la carpeta que contiene el archivo Dockerfile. Luego use

```
docker build -t mqttserver:0.0.1 .
```
Si desea correr la imagen de docker por fuera del docker-compose use
```                           
docker run -p 1883:1883 -p 8883:8883 -p 9001 mqttserver:0.0.1
```
Para subir la imagen a su DockerHub taggee primero su imagen usando como prefijo su nombre de usuario
```
docker tag mqttserver:0.0.1 domi0620/mqttserver:0.0.1
```
Finalmente suba la imagen a DockerHub con el comando push
```  
docker push domi0620/mqttserver:0.0.1 
```
## Despliegue local


Una vez haya creado su imagen y la haya subido a dockerhub, usted podrá usar el servicio en su docker compose:

```
version: '3.8'
services:
    mosquitto:
        image: domi0620/mqttserver:0.0.1
        ports:
            - 1883:1883
            - 8883:8883
            - 9001:9001
```


Luego use
```
docker-compose up
```

Esto creará un contenedor con el servidor en ejecución y se podrá conectar a él usando la url

```
mqtt://127.0.0.1:1883
```

A partir de esto podrá suscribirse y enviar datos a un topic

# Cliente MQTT para pruebas

Puede usar cualquier cliente de su elección. MQTTX es adecuado para testear los envíos y las suscripciones.
```
https://mqttx.app/
```

https://www.eclipse.org/paho/index.php?page=clients/js/index.php

# Despliegue
Para poder desplegarlo, puede llegar a necesitar que su servicio MQTT esté contenido en alguna ruta
```
mqtt://www.midominio.com/miserviciomqtt
```
En lugar de 
```
mqtt://www.midominio.com:1883
```
Porque le resta posibilidades de organizar los paths de su servicio

Para enrutar servicios, multiplexar puerto y generar una configuración completa de sus rutas use NGINX.


Entonces puede el siguiente docker-compose
```
version: '3.8'
services:
    mosquitto:
        image: eclipse-mosquitto:2
        ports:
            - 1883:1883
            - 8883:8883
            - 9001:9001
        volumes:
            - ./mosquitto.conf:/mosquitto/config/mosquitto.conf
    mqtt_proxy:
        image: nginx
        volumes:
            - ./nginx.conf:/etc/nginx/nginx.conf
        ports:
            - 8080:8080
``` 

El servicio de mosquitto puede ser directamente el servicio de eclipse o una imagen construida con el archivo de configuración de mosquitto (mosquitto.conf)

Luego está el servicio de proxy por medio de NGINX. Este servidor se configura por medio de un archivo llamado nginx.conf
```
worker_processes 1;

events {
    worker_connections 1024;
}

http {
    server {
        listen 8080;
        location /icesimqtt {
            proxy_pass http://mosquitto:1883;
            proxy_http_version 1.1;
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header Connection "upgrade";
        }
        location /mqtt {
            proxy_pass http://mosquitto:9001; #el prefijo http se usa por convención
            proxy_http_version 1.1;
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header Connection "upgrade";
        }
    }

}
```
Note que tendrá un límite de 1024 conexiones y que estará escuchando en el puerto 8080. Tendrá dos rutas:
<ol>
<li>El path base que usted elija y cuando los cliente se conecten a mqtt://www.midominio:8080/icesimqtt, el servidor NGINX redirigirá la solicitud a mosquitto:1883. Donde "mosquitto" es el nombre del servicio descrito en el compose.</li>
<li>El path por defecto para MQTT over WebSockets. El servicio de mosquitto tiene habilitada en la configuración que un cliente pueda conectarse o bien por MQTT o bien por Websockets. A MQTT se conectan por medio del puerto 9001 y lo hará por medio de ws://www.midominio:8080/mqtt</li>
</ol>

