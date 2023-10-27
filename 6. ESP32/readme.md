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