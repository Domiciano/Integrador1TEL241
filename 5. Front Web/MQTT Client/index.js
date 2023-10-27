const okbtn = document.getElementById('okbtn');
client = new Paho.MQTT.Client('broker.hivemq.com', Number(8000), "webmqttuser1");

//Listener de mensajes
client.onMessageArrived = function(msg){
    console.log("Arrived!: "+msg.payloadString);
}

//Función para conectarse al broker
client.connect({onSuccess:function(){
    console.log("conectado!")
    client.subscribe("test/101/beta");
}});

//Función para enviar un mensaje a un topic
function sendMessage(){
    message = new Paho.MQTT.Message("Hello from JS");
    message.destinationName = "test/101/beta";
    client.send(message);
};