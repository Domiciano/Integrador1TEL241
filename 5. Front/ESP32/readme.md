Getting started arduino ESP32


Siga esta guía para instalar ESP32 en su arduino
```
https://randomnerdtutorials.com/installing-the-esp32-board-in-arduino-ide-windows-instructions/
```


Vaya a File > Preferences

En la sección Additional Boards Manager URLs copie y pegue la siguiente dirección:

```
https://raw.githubusercontent.com/espressif/arduino-esp32/gh-pages/package_esp32_index.json
```

Luego vaya a Board > Boards Manager

Busque ESP32 y le debe salir

```
esp32 by Espressif Systems
```

Seleccionelo y de en aceptar

Espere a la instalación

Después de eso, ya puede seleccionar su ESP32 en el menú de boards.

Si compró la board recomendada use

```
ESP32-WROOM-DA
```


## Scanner de redes
Programe esto en el ESP32 para reconocer las redes a su alrededor

```
#include <WiFi.h>

void setup() {
  Serial.begin(9600);
  WiFi.mode(WIFI_STA);
  WiFi.disconnect();
  delay(100);
  Serial.println("Setup done");
}

void loop() {
  Serial.println("Scan start");

  // WiFi.scanNetworks will return the number of networks found
  int n = WiFi.scanNetworks();
  Serial.println("scan done");
  if (n == 0) {
      Serial.println("no networks found");
  } else {
    Serial.print(n);
    Serial.println(" networks found");
    for (int i = 0; i < n; ++i) {
      // Print SSID and RSSI for each network found
      Serial.print(i + 1);
      Serial.print(": ");
      Serial.print(WiFi.SSID(i));
      Serial.print(" (");
      Serial.print(WiFi.RSSI(i));
      Serial.print(")");
      Serial.println((WiFi.encryptionType(i) == WIFI_AUTH_OPEN)?" ":"*");
      delay(10);
    }
  }
  Serial.println("");

  // Wait a bit before scanning again
  delay(5000);
}

```

## Conectarse a una red Wi-fi

Use el siguiente código para conectarse a una red
```
#include <WiFi.h>

const char* ssid = "SU_SSID";
const char* password = "SU_CONTRASENA";


void setup() {
  Serial.begin(9600);
  WiFi.mode(WIFI_STA);
  initWiFi();

}

void loop() {
  
}

void initWiFi() {
  WiFi.begin(ssid, password);
  Serial.print("Connecting to WiFi ..");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(1000);
  }
  Serial.println("Connected!!");
  Serial.println(WiFi.localIP());
  
}

```


https://randomnerdtutorials.com/esp32-http-get-post-arduino/

Necesitas instalar la biblioteca Arduino_JSON. Puedes instalar esta biblioteca en el Administrador de Bibliotecas de Arduino IDE. Simplemente ve a Sketch > Include Library > Manage Libraries y busca el nombre de la biblioteca como Arduino_JSON


```
#include <WiFi.h>
#include <HTTPClient.h>

const char* ssid = "SSID";
const char* password = "CONTRASEÑA";

String serverName = "http://pokeapi.co/api/v2/pokemon/ditto";

unsigned long lastTime = 0;
unsigned long timerDelay = 5000;

void setup() {
  Serial.begin(115200); 

  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
 
  Serial.println("Timer set to 5 seconds (timerDelay variable), it will take 5 seconds before publishing the first reading.");
}

void loop() {
  //Send an HTTP POST request every 10 minutes
  if ((millis() - lastTime) > timerDelay) {
    //Check WiFi connection status
    if(WiFi.status()== WL_CONNECTED){
      HTTPClient http;

      String serverPath = serverName;
      
      // Your Domain name with URL path or IP address with path
      http.begin(serverPath.c_str());
      
      // If you need Node-RED/server authentication, insert user and password below
      //http.setAuthorization("REPLACE_WITH_SERVER_USERNAME", "REPLACE_WITH_SERVER_PASSWORD");
      
      // Send HTTP GET request
      int httpResponseCode = http.GET();
      
      if (httpResponseCode>0) {
        Serial.print("HTTP Response code: ");
        Serial.println(httpResponseCode);
        String payload = http.getString();
        Serial.println(payload);
      }
      else {
        Serial.print("Error code: ");
        Serial.println(httpResponseCode);
      }
      // Free resources
      http.end();
    }
    else {
      Serial.println("WiFi Disconnected");
    }
    lastTime = millis();
  }
}
```

## GET Request
```
#include <WiFi.h>
#include <HTTPClient.h>


const char* ssid = "PUBLICA";
const char* password = "";


String url = "https://pokeapi.co/api/v2/pokemon/ditto";


void setup() {
  Serial.begin(9600);
  WiFi.mode(WIFI_STA);
  initWiFi();
  HTTPClient http;
  http.begin(url.c_str());
  int httpResponseCode = http.GET();

  if (httpResponseCode > 0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    String payload = http.getString();
    Serial.println(payload);
  } else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }
  http.end();
}

void loop() {
  
}

void initWiFi() {
  WiFi.begin(ssid, password);
  Serial.print("Connecting to WiFi ..");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(1000);
  }
  Serial.println("Connected!!");
  Serial.println(WiFi.localIP());
  
}
```

## POST Request

```
#include <WiFi.h>
#include <HTTPClient.h>


const char* ssid = "PUBLICA";
const char* password = "";


String url = "https://facelogprueba.firebaseio.com/pokemons.json";


void setup() {
  Serial.begin(9600);
  WiFi.mode(WIFI_STA);
  initWiFi();
  HTTPClient http;
  http.begin(url.c_str());
  http.addHeader("Content-Type", "application/json");
  //ENVIO DE DATOS
  int httpResponseCode = http.POST("{\"api_key\":\"tPmAT5Ab3j7F9\",\"sensor\":\"BME280\",\"value1\":\"24.25\",\"value2\":\"49.54\",\"value3\":\"1005.14\"}");


  //RECEPCIÓN
  if (httpResponseCode > 0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    String payload = http.getString();
    Serial.println(payload);
  } else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }

  http.end();
}

void loop() {
  
}

void initWiFi() {
  WiFi.begin(ssid, password);
  Serial.print("Connecting to WiFi ..");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(1000);
  }
  Serial.println("Connected!!");
  Serial.println(WiFi.localIP());
  
}
```
## Decodificación JSON

```
#include <WiFi.h>
#include <HTTPClient.h>
#include <Arduino_JSON.h>



const char* ssid = "PUBLICA";
const char* password = "";


String url = "https://facelogprueba.firebaseio.com/pokemons.json";


void setup() {
  Serial.begin(9600);
  WiFi.mode(WIFI_STA);
  initWiFi();
  HTTPClient http;
  http.begin(url.c_str());
  http.addHeader("Content-Type", "application/json");
  //ENVIO DE DATOS
  int httpResponseCode = http.POST("{\"api_key\":\"tPmAT5Ab3j7F9\",\"sensor\":\"BME280\",\"value1\":\"24.25\",\"value2\":\"49.54\",\"value3\":\"1005.14\"}");


  //RECEPCIÓN
  if (httpResponseCode > 0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    String payload = http.getString();
    Serial.println(payload);

    JSONVar myObject = JSON.parse(payload);
    Serial.println(myObject);

    JSONVar keys = myObject.keys();
    for (int i = 0; i < keys.length(); i++) {
      JSONVar value = myObject[keys[i]];
      Serial.print(keys[i]);
      Serial.print(" = ");
      Serial.println(value);
    }


  } else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }

  http.end();
}

void loop() {
  
}

void initWiFi() {
  WiFi.begin(ssid, password);
  Serial.print("Connecting to WiFi ..");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(1000);
  }
  Serial.println("Connected!!");
  Serial.println(WiFi.localIP());
  
}
```

## Codificando en JSON

https://microdigisoft.com/esp32-with-arduino-json-using-arduino-ide/


## Conexión a MQTT Server
wss://i2thub.icesi.edu.co:5443/giscel/ws
https://randomnerdtutorials.com/esp32-mqtt-publish-subscribe-arduino-ide/



PubSubClient by Nick O'Leary
https://github.com/knolleary/pubsubclient

```
#include <WiFi.h>
#include <PubSubClient.h>

// Datos de la red WiFi
const char* ssid = "i2t_research";
const char* password = "marcopolo";

// Datos del servidor MQTT
const char* mqtt_server = "192.168.1.8";
const int mqtt_port = 1883;
const char* mqtt_topic = "alfa";

WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  Serial.begin(9600);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Conectando a la red WiFi...");
  }

  Serial.println("Conectado a la red WiFi");
  Serial.println("Conectando al servidor MQTT...");

  client.setServer(mqtt_server, mqtt_port);
  client.setCallback(callback);

  while (!client.connected()) {
    if (client.connect("ESP32Client")) {
      Serial.println("Conectado al servidor MQTT");
      client.subscribe(mqtt_topic);
    } else {
      Serial.print("Error al conectar al servidor MQTT. Código de error: ");
      Serial.println(client.state());
      delay(2000);
    }
  }
}

void loop() {
  client.loop();
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Mensaje recibido en el tópico: ");
  Serial.println(topic);

  Serial.print("Contenido: ");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();
}
```



