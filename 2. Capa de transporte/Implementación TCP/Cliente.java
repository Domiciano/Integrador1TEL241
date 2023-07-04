import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        while(true){
            String resquest = scanner.nextLine();
            Socket socket = new Socket("127.0.0.1", 6000);

            OutputStream os = socket.getOutputStream();
            os.write(resquest.getBytes());
            os.flush();

            //Esperamos la response
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            is.read(buffer);
            String response = new String(buffer).trim();
            System.out.println(response);

            socket.close();

        }






    }

}
