import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        
        try {
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            DataInputStream in = new DataInputStream(this.socket.getInputStream());

            
            System.out.println(in.readUTF());
            out.writeUTF("Hello Alice");

            in.close();

            out.flush();
            out.close();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
