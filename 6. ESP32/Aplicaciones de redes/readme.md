# Red de acceso en laboratorio
const char* ssid = "LABREDES";
const char* password = "F0rmul4-1";

# Aplicaciones de redes


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


# Access point 

```
#include <WiFi.h>
#include <WiFiAP.h>


const char* ssid = "Telematica";
const char* password = "alfabetagamma";

IPAddress AP_LOCAL_IP(192, 168, 1, 1);
IPAddress AP_GATEWAY_IP(192, 168, 1, 254);
IPAddress AP_NETWORK_MASK(255, 255, 255, 192);

void setup() {
  Serial.begin(115200);
  while (!Serial);

  WiFi.softAPConfig(AP_LOCAL_IP, AP_GATEWAY_IP, AP_NETWORK_MASK);
  WiFi.softAPsetHostname("Telematica");
  if (!WiFi.softAP(ssid, password)){
        Serial.println("Soft AP creation failed.");
        while (1);
  }
  Serial.print("AP Name SSID: ");
  Serial.println(WiFi.softAPSSID());
  Serial.print("AP IP Address: ");
  Serial.println(WiFi.softAPIP());
  Serial.print("AP Hostname: ");
  Serial.println(WiFi.softAPgetHostname());
  Serial.print("AP Mac Address: ");
  Serial.println(WiFi.softAPmacAddress());
  Serial.print("AP Subnet: ");
  Serial.println(WiFi.softAPSubnetCIDR());
}

void loop() {
  Serial.print("Num of Connected Clients : ");
  Serial.println(WiFi.softAPgetStationNum());
  delay(5000);
}
```


