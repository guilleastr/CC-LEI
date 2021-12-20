package Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import packages.PackageParser;

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
            // getting write and read streams from socket
            //DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            DataInputStream in = new DataInputStream(this.socket.getInputStream());

           

            
            PackageParser pp= new PackageParser();
            byte[] response= pp.parsePackage(in.readAllBytes()).execute();
            //DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            //out.write(response);
            System.out.println("Closing");
            in.close();

//            out.flush();
//            out.close();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
