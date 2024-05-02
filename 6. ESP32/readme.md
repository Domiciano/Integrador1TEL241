# Getting started arduino ESP32


Siga esta guía para instalar ESP32 en su arduino
```
https://randomnerdtutorials.com/installing-the-esp32-board-in-arduino-ide-windows-instructions/
```

1. Abra el programa de Arduino IDE

2. Vaya a File > Preferences

3. En la pestaña de settings ubique la sección 'Additional Boards Manager URLs' copie y pegue la siguiente dirección:

```
https://raw.githubusercontent.com/espressif/arduino-esp32/gh-pages/package_esp32_index.json
```

4. Luego vaya a Board > Boards Manager

5. Busque ESP32 y le debe salir lo siguiente

```
esp32 by Espressif Systems
```

6. Seleccionelo y de en Aceptar y espere a la instalación

Después de eso, ya puede seleccionar su ESP32 en el menú de boards. <br><br>

Tools > Boards > esp32 > ESP32-WROOM-DA Module

## Plantilla
```
void setup() {
  Serial.begin(115200);
}

void loop() {
  delay(1000);
  Serial.println("Hello World");
}

void serialEvent() {
  if (Serial.available() > 0) {
    String data = Serial.readStringUntil('\n');
  }
}
```
