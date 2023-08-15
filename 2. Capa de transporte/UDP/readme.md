Para crear un Peer UDP, se puede guiar usando este código
```
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class UDPPeer {

    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket(6001);



        //Lectura
        new Thread(() -> {
            try {
                while (true) {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String message = new String(buffer);
                    System.out.println(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();

        //Escritura
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            DatagramPacket packet = new DatagramPacket(
                    line.getBytes(),
                    line.length(),
                    InetAddress.getByName("127.0.0.1"),
                    6000
            );
            socket.send(packet);

        }

    }

}
```
