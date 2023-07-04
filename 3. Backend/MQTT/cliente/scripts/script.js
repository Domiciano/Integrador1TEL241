const okbtn = document.getElementById('okbtn');

client = new Paho.MQTT.Client('127.0.0.1', Number(9001), "clientId");

client.onMessageArrived = function(msg){
    console.log("Arrived!: "+msg.payloadString);
}

client.connect({onSuccess:function(){
    console.log("conectado!")
    client.subscribe("alfa");
}});


okbtn.addEventListener('click', function(){
    message = new Paho.MQTT.Message("Hello from JS");
    message.destinationName = "alfa";
    client.send(message);
});


