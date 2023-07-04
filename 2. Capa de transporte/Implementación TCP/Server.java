import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(6000);

        while (true) {
            System.out.println("Esperando solicitud");
            Socket socket = server.accept();
            System.out.println("Cliente conectado");

            //Request
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            is.read(buffer);
            String request = new String(buffer).trim();
            System.out.println(request);

            //Procesar el request
            String response = "";
            if (request.equals("whattimeisit")) {
                Date date = new Date();
                response = date.toString();
            }else if(request.startsWith("SUM")){
                request = request.replace("SUM", "");
                request = request.replace("(", "");
                request = request.replace(")", "");
                String[] ops = request.split(",");
                int A = Integer.parseInt(ops[0]);
                int B = Integer.parseInt(ops[1]);
                response = (A+B)+"";
            }

            //Enviar el response
            OutputStream os = socket.getOutputStream();
            os.write(response.getBytes());

            //Desconectar
            socket.close();

        }

    }


}
