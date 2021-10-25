import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        String serverIP = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("connecting to " + serverIP + ":" + port);
        try {
            Socket client = new Socket(serverIP, port);

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            System.out.println("socket information: "+client.getPort());

            out.writeUTF("Hello Bob");
            System.out.println(in.readUTF());

            in.close();

            out.flush();
            out.close();

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}