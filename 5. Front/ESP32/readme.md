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

## Plantilla
```
void setup() {
  Serial.begin(9600);
}

void loop() {
}
void serialEvent() {
  if (Serial.available() > 0) {
    String data = Serial.readStringUntil('\n');
  }
}
```

## GET Request
```
#include <WiFi.h>
#include <HTTPClient.h>


const char* ssid = "LABREDES";
const char* password = "F0rmul4-1";


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

```
#include <Arduino_JSON.h>

...

JSONVar myObject;
myObject["key1"] = "value1";
myObject["key2"] = "value2";
String jsonString = JSON.stringify(myObject);
Serial.println(jsonString);
```



## Conexión a MQTT Server SIN SSL
wss://i2thub.icesi.edu.co:5443/giscel/ws
https://randomnerdtutorials.com/esp32-mqtt-publish-subscribe-arduino-ide/



PubSubClient by Nick O'Leary
https://github.com/knolleary/pubsubclient

```
#include <WiFi.h>
#include <PubSubClient.h>

// Configuración de la red Wi-Fi
const char* ssid = "PUBLICA";
const char* password = "";

// Configuración del servidor MQTT
const char* mqttServer = "broker.hivemq.com";
const int mqttPort = 1883;

// Configuración del topic
const char* topic = "test/101/alfa";

// Objeto WiFiClient
WiFiClient wifiClient;

// Objeto PubSubClient
PubSubClient mqttClient(wifiClient);

void setup() {
  // Inicializa la comunicación serial
  Serial.begin(115200);

  // Conecta a la red Wi-Fi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  // Inicializa el cliente MQTT
  mqttClient.setServer(mqttServer, mqttPort);
  mqttClient.setCallback(callback);

  // Intenta conectarse al servidor MQTT
  while (!mqttClient.connected()) {
    Serial.println("Intentando conectar al servidor MQTT...");
    if (mqttClient.connect("Clienteasdasd")) {
      Serial.println("Conectado al servidor MQTT!");
    } else {
      Serial.print("Error al conectar: ");
      Serial.println(mqttClient.state());
      delay(5000);
    }
  }

  // Suscríbete al topic
  mqttClient.subscribe(topic);
}

void callback(char* topic, byte* payload, unsigned int length) {
  // Imprime el mensaje recibido
  Serial.print("Mensaje recibido en el topic ");
  Serial.print(topic);
  Serial.print(": ");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();
}

void loop() {
  // Procesa los mensajes del servidor MQTT
  mqttClient.loop();
}
```

