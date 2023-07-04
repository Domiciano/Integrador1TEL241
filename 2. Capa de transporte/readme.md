<img width="256" src="https://www.icesi.edu.co/launiversidad/images/La_universidad/logo_icesi.png">

# Protocolos de tranporte

## Protocolo TCP

## Protocolo UDP



## Protocolo QUIC

El protocolo QUIC (Quick UDP Internet Connections) es un protocolo de transporte en red desarrollado por Google con el objetivo de mejorar el rendimiento de las comunicaciones en Internet. A diferencia de los protocolos tradicionales como TCP (Transmission Control Protocol) que se basan en conexiones TCP, QUIC utiliza el protocolo UDP (User Datagram Protocol) como capa de transporte.

Aquí hay algunos puntos clave sobre QUIC:

1. Rendimiento mejorado: QUIC fue diseñado para ofrecer una menor latencia y una mejor capacidad de recuperación de errores en comparación con TCP. Logra esto mediante la combinación de varias características, como la encriptación integrada, el establecimiento de conexiones más rápidas y la multiplexación de conexiones.

2. Multiplexación de conexiones: QUIC permite que múltiples flujos de datos se envíen dentro de una única conexión, lo que reduce la necesidad de establecer y mantener múltiples conexiones TCP. Esto mejora la eficiencia y el rendimiento al evitar la sobrecarga de establecimiento de conexiones.

3. Encriptación integrada: QUIC cifra automáticamente todo el tráfico de extremo a extremo utilizando TLS (Transport Layer Security). Esto proporciona seguridad y privacidad de manera predeterminada, sin necesidad de establecer una conexión TLS adicional como lo requiere TCP.

4. Conexiones más rápidas: QUIC reduce la latencia al realizar el handshake de conexión más rápido en comparación con TCP. Esto se logra a través de un mecanismo de "handshake 0-RTT" que permite al cliente reanudar una conexión previa sin tener que esperar la confirmación del servidor.

5. Resiliencia a cambios de red: QUIC está diseñado para ser más resistente a los cambios de red, como cambios de dirección IP o cambios entre conexiones de red diferentes (por ejemplo, de Wi-Fi a datos móviles). Esto permite una conexión más confiable y continua en entornos de red cambiantes.

6. Despliegue gradual: QUIC se implementa en la capa de transporte, lo que significa que puede ser utilizado por aplicaciones específicas sin requerir cambios en la pila de protocolos subyacente. Esto permite un despliegue gradual y una adopción más fácil en comparación con la introducción de un nuevo protocolo de nivel superior.

QUIC se está adoptando rápidamente y es compatible con muchos servicios en línea populares. Proporciona mejoras significativas en el rendimiento y la seguridad de las comunicaciones en Internet. A medida que continúa su evolución y despliegue, es probable que veamos un aumento en su uso y beneficios para los usuarios finales.


## Intercambio de datos entre Alice y Bob
Intercambio de datos entre el cliente (Alice) y el servidor (Bob) utilizando el protocolo QUIC:

1. Establecimiento de la conexión:
   - Alice inicia una solicitud de conexión QUIC al servidor Bob. Esta solicitud se envía utilizando UDP en lugar de TCP.
   - La solicitud de conexión QUIC incluye información como la dirección IP de Bob, el puerto, la versión del protocolo y los parámetros de cifrado.
   - Bob recibe la solicitud de conexión QUIC y verifica los parámetros. Si Bob está dispuesto a aceptar la conexión, responde a la solicitud.

2. Handshake de conexión:
   - Una vez que Bob acepta la solicitud de conexión, se inicia un proceso de handshake de conexión.
   - Durante el handshake, Alice y Bob negocian los parámetros de seguridad y establecen las claves de cifrado para la comunicación.
   - El handshake de QUIC incluye un mecanismo de "handshake 0-RTT" que permite a Alice reanudar una conexión previa sin tener que esperar la confirmación del servidor. Esto acelera el proceso de establecimiento de la conexión.

3. Intercambio de datos:
   - Después de que se complete el handshake de conexión, Alice y Bob pueden comenzar a intercambiar datos.
   - Los datos se envían en forma de "flujos" dentro de la conexión QUIC. Cada flujo se identifica mediante un número único y puede contener datos de aplicación específicos.
   - QUIC permite la multiplexación de flujos, lo que significa que varios flujos pueden enviarse simultáneamente dentro de la misma conexión. Esto mejora la eficiencia y reduce la latencia al evitar la necesidad de establecer múltiples conexiones.

4. Control de flujo y congestión:
   - QUIC incorpora mecanismos de control de flujo y congestión para garantizar un rendimiento óptimo y evitar la congestión de la red.
   - El control de flujo permite que el receptor (Bob) controle la cantidad de datos que el remitente (Alice) puede enviar en un momento dado, evitando así que Bob se vea abrumado por una cantidad excesiva de datos.
   - El control de congestión ajusta dinámicamente la velocidad de envío de los datos para evitar la congestión en la red y mantener un flujo de datos estable.

5. Terminación de la conexión:
   - Una vez que Alice y Bob han terminado de intercambiar datos y no necesitan la conexión QUIC, pueden cerrarla.
   - El cierre de la conexión QUIC implica un proceso de handshake de cierre similar al handshake de conexión, donde ambos lados confirman su disposición para cerrar la conexión.
   - Después de que se complete el cierre de la conexión, la conexión QUIC se termina y ya no se pueden intercambiar más datos.

Estos pasos proporcionan una visión general del proceso de intercambio de datos inicial en QUIC. Cabe destacar que QUIC es un protocolo en evolución y los detalles exactos pueden variar según la implementación específica y las versiones del protocolo.


## Proceso de handshake


Por supuesto, aquí tienes una explicación paso a paso del proceso de handshake entre Alice y Bob en QUIC:

1. Solicitud de conexión (Client Hello):
   - Alice, el cliente, envía un mensaje llamado "Client Hello" a Bob, el servidor, para iniciar el handshake de conexión.
   - El mensaje "Client Hello" contiene información como la versión de QUIC que Alice está utilizando, una lista de los cifrados y extensiones de seguridad que Alice admite, y un nonce aleatorio.

2. Respuesta del servidor (Server Hello):
   - Bob, el servidor, recibe el mensaje "Client Hello" de Alice y selecciona los parámetros de seguridad y cifrado para la conexión.
   - Bob responde con un mensaje llamado "Server Hello" que contiene información como la versión de QUIC acordada, los parámetros de seguridad seleccionados y un nonce aleatorio.

3. Intercambio de claves y parámetros de seguridad:
   - Alice y Bob intercambian sus claves públicas y acuerdan los parámetros de seguridad necesarios para establecer una conexión segura.
   - Utilizando estas claves y parámetros, Alice y Bob generan las claves de cifrado y autenticación que utilizarán para proteger los datos de la conexión.

4. Verificación y autenticación:
   - Alice y Bob verifican la autenticidad del otro. Esto se puede hacer utilizando certificados digitales firmados por una entidad de confianza o mediante otros mecanismos de autenticación.
   - Si la verificación y autenticación son exitosas, Alice y Bob confirman la autenticidad y continúan con el proceso de handshake.

5. Establecimiento de la conexión segura:
   - Una vez que las claves de cifrado y autenticación se han intercambiado y se ha realizado la verificación, Alice y Bob pueden establecer una conexión segura utilizando estas claves.
   - A partir de este punto, todos los datos intercambiados entre Alice y Bob se cifran y se autentican utilizando las claves acordadas.

## 0-RTT
Para poder utilizar el handshake 0-RTT en QUIC, se necesita que el proceso completo del handshake haya sucedido al menos una vez entre Alice y Bob. Durante el primer handshake exitoso, se establecen los parámetros de seguridad y se generan las claves necesarias para cifrar y autenticar los datos. Una vez que se ha completado este proceso inicial, en los sucesivos intentos de conexión, Alice puede aprovechar el handshake 0-RTT para reanudar la conexión sin tener que esperar la confirmación del servidor. Esto acelera el establecimiento de la conexión, ya que se pueden enviar datos de aplicación antes de que se complete un nuevo handshake completo.

Es importante tener en cuenta que el uso de handshake 0-RTT implica ciertos riesgos de seguridad, ya que se basa en la reanudación de una conexión previa. Por lo tanto, debe implementarse con precaución y considerar las implicaciones de seguridad adecuadas.

## Mecanismo de pérdida de paquetes

En QUIC, el mecanismo para manejar la pérdida de paquetes se basa en el envío de paquetes de retransmisión y el uso de un mecanismo de control de congestión. A continuación, te explico cómo funciona:

1. Números de secuencia y ACK:
   - Al igual que en TCP, QUIC utiliza números de secuencia para identificar y ordenar los paquetes.
   - El receptor (ya sea Alice o Bob) envía paquetes de ACK (Acknowledgment) para informar al emisor sobre los paquetes que ha recibido correctamente.
   - Estos paquetes de ACK incluyen información sobre los números de secuencia de los paquetes recibidos y confirmados.

2. Paquetes de retransmisión:
   - Si el emisor (ya sea Alice o Bob) detecta que un paquete no ha sido confirmado por el receptor después de un período de tiempo determinado, se considera perdido.
   - En este caso, el emisor retransmite el paquete no confirmado, lo que significa que lo envía nuevamente al receptor.
   - El receptor, al recibir el paquete retransmitido, puede reconocerlo utilizando el número de secuencia y, si no ha recibido previamente ese paquete, lo procesa correctamente.

3. Control de congestión:
   - QUIC incorpora un mecanismo de control de congestión para evitar la congestión en la red y garantizar un rendimiento óptimo.
   - Cuando se detecta la pérdida de un paquete, el emisor reduce la velocidad de envío para evitar una mayor congestión y pérdida de paquetes adicionales.
   - A medida que la conexión continúa, el emisor aumenta gradualmente la velocidad de envío si no se producen más pérdidas de paquetes.

4. Reconocimiento selectivo:
   - QUIC también implementa un mecanismo llamado "Selective Acknowledgment" (SACK) que permite al receptor informar al emisor sobre los paquetes individuales que ha recibido correctamente.
   - Con SACK, el emisor puede retransmitir solo los paquetes específicos que se hayan perdido en lugar de retransmitir todo el flujo de datos desde un punto anterior.

En resumen, en caso de que haya una pérdida de paquetes en QUIC, el emisor retransmitirá los paquetes no confirmados y ajustará la velocidad de envío para evitar la congestión adicional. El receptor informará al emisor sobre los paquetes que ha recibido correctamente utilizando paquetes de ACK y, si es compatible, también puede utilizar SACK para una retransmisión selectiva. Estos mecanismos de QUIC ayudan a garantizar una transmisión confiable y eficiente de datos incluso en presencia de pérdida de paquetes.



Cuando Alice inicia una conexión QUIC con Bob, Alice incluye la dirección IP y el puerto desde los cuales está enviando la solicitud en el mensaje de inicio de conexión (Client Hello). Este mensaje se envía a través de UDP al servidor Bob.
