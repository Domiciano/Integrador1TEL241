import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6000);

        //Proceso asincrono de lectura
        new Thread(()->{
            while (true){
                try {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(
                            buffer,
                            buffer.length
                    );
                    socket.receive(packet);
                    String received = new String(buffer).trim();
                    System.out.println(received);
                    System.out.println("From: "+packet.getSocketAddress().toString());
                    System.out.println("From: "+packet.getAddress().toString());
                }catch (IOException ex){ex.printStackTrace();}
            }
        }).start();

        //Escritura
        Scanner scanner = new Scanner(System.in);
        while (true){
            String message = scanner.nextLine();
            DatagramPacket packet = new DatagramPacket(
                    message.getBytes(),
                    message.getBytes().length,
                    InetAddress.getByName("192.168.130.76"),
                    6000
            );//192.168.130.65
            socket.send(packet);
        }


    }

}
