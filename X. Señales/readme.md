# Transformada de Fourier
¿Siempre has querido transformar tus señales del tiempo a la frencuencia? No se diga más, sigue estas sencillas instrucciones para hacer la transformada rápida de fourier de una vez por todas

## 1. Normalizar
Lo primero es que todas las señales tomadas por los sensores pueden tener amplitudes diferentes. Para normalizar el proceso debes normalizar la señal. Esta normalización elimina los componentes de DC y además hace que la amplitud sea 1

```java
double[] signal;
...
signal = DFTUtils.normalize(signal);
```
## 2. Ahora obtenga el vector de FFT
Gracias al método de DFT, puede obtener la transformada, esto lo puede almacenar el un arreglo de double
```java
double[] spectrum = DFTUtils.dftSpectrum(signal);
```

## 3. Ahora obtenga el vector de frecuencias
Finalmente, necesitarás saber las frecuencias correspondientes a cada elemento en el vector de FFT, lo puedes obtener así
```java
double[] freqs = DFTUtils.dftFreq(signal, fs);
```
