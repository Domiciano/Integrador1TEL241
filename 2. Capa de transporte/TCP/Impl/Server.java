import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        //1. Hago que el server esté atento a conexiones
        ServerSocket server = new ServerSocket(5001);
        System.out.println("Esperando conexión");
        //3. Conexión aceptada
        Socket socket = server.accept();

        System.out.println("Conexión aceptada: "+socket.getPort());
        System.out.println("Conexión aceptada: "+socket.getInetAddress().getHostName());

        //5. Server recibe mensaje
        byte[] buffer = new byte[300];
        socket.getInputStream().read(buffer);
        String received = new String(buffer, "UTF-8");
        System.out.println(received.trim());

        //6. Server envía
        String mensaje = "Hola desde el server";
        socket.getOutputStream().write(mensaje.getBytes("UTF-8"));


        //new Thread(()->{
        // ... Codigo en segundo plano
        // }).start();
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

        //192.168.130.37
        Scanner scanner = new Scanner(System.in);
        while (true){
            String msg = scanner.nextLine();
            socket.getOutputStream().write(msg.getBytes("UTF-8"));
        }


    }


}
