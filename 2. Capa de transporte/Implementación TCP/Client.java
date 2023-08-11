import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        //2. Solicitud de conexión
        Socket socket = new Socket("127.0.0.1", 6000);
        System.out.println(socket.getPort());
        System.out.println("Conexión aceptada: "+socket.getInetAddress().getHostName());
        //Dirección de loopback -> 127.0.0.1
        //localhost
        //192.168.130.37

        /*
        //4. Cliente envia mensaje
        String mensaje = "Universidad Icesi";
        socket.getOutputStream().write(mensaje.getBytes("UTF-8"));

        //7. Cliente recibe
        byte[] buffer = new byte[300];
        socket.getInputStream().read(buffer);
        String received = new String(buffer);
        System.out.println(received);
        */

        new Thread(()->{
            while(true){
                try {
                    byte[] bf = new byte[300];
                    socket.getInputStream().read(bf);
                    String rec = new String(bf, "UTF-8");
                    System.out.println(rec.trim());
                }catch (IOException ex){ex.printStackTrace();}
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (true){
            String msg = scanner.nextLine();
            socket.getOutputStream().write(msg.getBytes("UTF-8"));
        }

    }
}
