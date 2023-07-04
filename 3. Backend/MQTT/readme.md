<img width="256" src="https://www.icesi.edu.co/launiversidad/images/La_universidad/logo_icesi.png">

# Protocolo MQTT
Para crear un servidor MQTT simple cree un proyecto con la siguiente estructura

```
* Mosquitto
   * config
      - mosquitto.conf
   - docker-compose.yml
```

Descripción: Crear una carpeta Mosquitto con una carpeta config y un archivo docker-compose.yml. Dentro de la carpeta config, un archivo mosquitto.conf

En el archivo mosquitto.conf escriba la configuración mínima necesaria
```
allow_anonymous true
listener 1883 0.0.0.0
```

Luego use el comando

```
docker-compose up
```

Esto creará un contenedor con el servidor en ejecución y se podrá conectar a él usando la url

```
mqtt://127.0.0.1:1883
```

A partir de esto podrá suscribirse y enviar datos a un topic

# Cliente MQTT

Puede usar cualquier cliente de su elección. MQTTX es adecuado para testear los envíos y las suscripciones.
```
https://mqttx.app/
```

