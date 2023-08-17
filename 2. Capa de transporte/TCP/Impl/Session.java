import java.io.IOException;
import java.net.Socket;

public class Session implements Runnable {
    private Socket socket;

    public Session(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] bf = new byte[300];
                socket.getInputStream().read(bf);
                String rec = new String(bf, "UTF-8").trim();
                ServerSupremo.sendBroadcast(rec);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void send(String message){
        try {
            socket.getOutputStream().write(message.getBytes());
        }catch (IOException ex){ex.printStackTrace();}
    }

}
