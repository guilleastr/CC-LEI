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

		try {
			// Singleton DirectoryManager, in order to make the file access global and
			// unique
			DirectoryManagerSingleton.getInstance();
			System.out.print("Write path to Watch: ");
			String PATH = new BufferedReader(new InputStreamReader(System.in)).readLine().toString();
			DirectoryManagerSingleton.init(PATH);

			// IP of the remote server
			System.out.println("(Y) for Conect to remote server, Anything to wait for a conection: ");
			String option = new BufferedReader(new InputStreamReader(System.in)).readLine().toString();

			Server server = new Server();
			Thread t = new Thread(server);

			if (option.equals("Y") || option.equals("y")) {
				System.out.print("IP remote Server: ");
				String ip = new BufferedReader(new InputStreamReader(System.in)).readLine();
				ControlClient controlClient = new ControlClient(ip);
				Thread c = new Thread(controlClient);
				c.start();

			}
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
