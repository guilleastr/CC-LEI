import java.net.*;
import java.io.*;


public class Server {


    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        System.out.println("Listening on port " + port);
        boolean running = true;
        try {
            ServerSocket ssocket = new ServerSocket(port);
            while (running) {
                ClientHandler ch = new ClientHandler(ssocket.accept());
                Thread t = new Thread(ch);
                t.start();
            }
            ssocket.close();
        } catch (IOException e)  {
            e.printStackTrace();
        }

        
    }

    
}