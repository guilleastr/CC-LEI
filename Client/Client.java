package Client;

import java.net.*;

import packages.PackageBuilder;
import packages.PackageParser;

import java.io.*;

/**
 * simple and stupid TCP client
 */

public class Client {
    public static void main(String[] args) {
        String serverIP = args[0].toString();                    // get server IP
        //int port = Integer.parseInt(args[1]);           // get server port
        int port=8081;
        System.out.println("connecting to " + serverIP + ":" + port);
        
        try {
            // buffer to read from console
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose quit to exit, Anyhting to continue");
            String lineFromConsole = reader.readLine();  // read line from console

            while (!lineFromConsole.contentEquals("quit")) {
                Socket client = new Socket(serverIP, port);  // socket with the server ip and port

               

                System.out.println("File to check:");
                PackageBuilder pb= new PackageBuilder();
                byte[] data=pb.buildReadPacakge(reader.readLine());
                
                // write and read streams from socket
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                
                out.write(data);     // send to server
                
                DataInputStream in = new DataInputStream(client.getInputStream());
                
                System.out.println("Data Sent");
                PackageParser pp= new PackageParser();
                //pp.parsePackage(in.readAllBytes());

                in.close();

                out.flush();
                out.close();

                client.close();

                lineFromConsole = reader.readLine();   // read new line from console
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}