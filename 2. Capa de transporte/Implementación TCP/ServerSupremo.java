import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSupremo {

    private static ArrayList<Session> sessions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6000);
        while (true) {
            System.out.println("Esperando cliente");
            Socket socketClient = server.accept();
            System.out.println("Cliente conectado");
            Session session = new Session(socketClient);
            new Thread(session).start();
            sessions.add(session);
        }
    }

    public static void sendBroadcast(String message){
        for(Session session : sessions){
            session.send(message);
        }
    }

}
