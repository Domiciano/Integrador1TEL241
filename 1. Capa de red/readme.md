<img width="256" src="https://www.icesi.edu.co/launiversidad/images/La_universidad/logo_icesi.png">

# Protocolo IP

## Interfaces

```
import java.io.IOException;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Enumeration<NetworkInterface> networks =  NetworkInterface.getNetworkInterfaces();
        while(networks.hasMoreElements()){
            NetworkInterface network = networks.nextElement();
            if(network.isUp()){
                System.out.println(network.getDisplayName());
                List<InterfaceAddress> addresses = network.getInterfaceAddresses();
                for(InterfaceAddress address : addresses){
                    System.out.println("  >>>"+address);
                }
            }
        }
    }
}

```
