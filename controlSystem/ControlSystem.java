package controlSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Client.ControlClient;
import Server.Server;
import file.DirectoryManagerSingleton;

/**
 * ControlSystem will inizialize a Server, which will answer request from other
 * servers, and ControlClient, which will be sending periodically information
 * about the files in the system
 * 
 *
 */
public class ControlSystem {

	public static final String FULL_PATH = "C:/Users/LENOVO/Desktop/Erasmus/CC/Practica/TP02/check";

	/**
	 * Main execution
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int port;
		try {
			// Singleton DirectoryManager, in order to make the file access global and
			// unique
			DirectoryManagerSingleton.getInstance();
			DirectoryManagerSingleton.init(FULL_PATH);

			// Star parameters of the server: Command input
			// Port of this server
//			System.out.print("Port of server: ");
			

			// IP of the remote server
			System.out.print("IP remote Server:");
			String ip = new BufferedReader(new InputStreamReader(System.in)).readLine();

			// Port of the remote server
//			System.out.print("Port of remote server (0 for not connecting):");
//			port = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
			Server server = new Server();
			Thread t = new Thread(server);
			ControlClient controlClient = new ControlClient(ip);
			Thread c = new Thread(controlClient);

			// Throw Server and ControlClient on a thread each

			c.start();

			t.start();

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
