# Toma de datos

El siguiente programa está en desarrollo y permite por ahora conectarse a WiFi, hacer un GET Request y muestrear un conjunto de datos para producir un JSON

```c++
  #include <WiFi.h>
  #include <HTTPClient.h>
  #include <Arduino_JSON.h>


  String url = "https://facelogprueba.firebaseio.com/integrador.json";


  const char* ssid = "LABREDES";
  const char* password = "F0rmul4-1";

  const int arrayLength = 20;
  int measurements[arrayLength];


  void setup() {
    Serial.begin(115200);
    WiFi.mode(WIFI_STA);
    //initWiFi();
  }

  void loop() {
    int measurement = random(4096);
    //Serial.println(measurement);
    delay(500);
  }

  void serialEvent() {
    if (Serial.available() > 0) {
      String data = Serial.readStringUntil('\n');
      if(data == "wifi"){
        initWiFi();
      }else if(data == "get"){
        GETRequest();
      }else if(data == "measure"){
        readSensors();
      }else{
        Serial.println(data);
      }
    }
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

  void GETRequest() {
    HTTPClient http;
    http.begin(url.c_str());
    int httpResponseCode = http.GET();
    Serial.println("Code: " + String(httpResponseCode) );
    String payload = http.getString();
    Serial.println(payload);
    http.end();
  }

  void readSensors(){
    for(int i=0 ; i<arrayLength ; i++){
      int measurement = random(4096);
      measurements[i] = measurement;
      delay(20); //50Hz
    }

    JSONVar testObj;
    testObj["type"] = "Espiral";
    testObj["samples"] = arrayLength;

    JSONVar jsonArray;
    for(int i=0 ; i<arrayLength ; i++){
      JSONVar reading;
      reading["t"] = i*20;
      reading["x"] = measurements[i];
      jsonArray[i] = reading;
    }
    testObj["readings"] = jsonArray;
    String json = JSON.stringify(testObj);
    Serial.println(json);

  }


```

Adicionalmente se desarrolló un endpoint capaz de recibir la información producida
```java
@PostMapping("sensor")
public ResponseEntity<?> sendData(@RequestBody Test test){
  var output = ResponseEntity.status(200).body(test);
  return output;
}
```
```java
public class Test {
    private String type;
    private int samples;
    private ArrayList<Reading> readings;
    //GETS y SETS
}
```
```java
public class Reading {
    private int t;
    private int x;
    //GETS y SETS
```
}
