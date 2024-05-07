```c++
#include <WiFi.h>
#include <HTTPClient.h>

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
    delay(20);
  }
}
```
