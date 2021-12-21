package controlSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Client.ControlClient;
import Server.Server;
import file.DirectoryManagerSingleton;

public class ControlSystem {

	public static final String FULL_PATH = "C:/Users/LENOVO/Desktop/Erasmus/CC/Practica/TP02/check";

	public static void main(String[] args) {

		int port;
		try {
			DirectoryManagerSingleton.getInstance();
			DirectoryManagerSingleton.init(FULL_PATH);
			
			System.out.print("Port of server: ");
			Server server = new Server(
					Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));

			System.out.print("IP remote Server:");
			String ip = new BufferedReader(new InputStreamReader(System.in)).readLine();
			System.out.print("Port of remote server:");
			port = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

			ControlClient controlClient = new ControlClient(ip, port);

			Thread c = new Thread(controlClient);
			Thread t = new Thread(server);
			t.start();
			c.start();

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
