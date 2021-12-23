package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import file.DirectoryManager;
import file.DirectoryManagerSingleton;
import packages.PackageBuilder;

public class ControlClient implements Runnable{

	private String ip;
	private int port;


	public ControlClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;

	}

	/**
	 *Starts the controlClient
	 */
	@Override
	public void run() {
		System.out.println("connecting to " + this.ip + ":" + this.port);
		try {
			DirectoryManager dm = DirectoryManagerSingleton.getInstance();

			System.out.println("CONTROLCLIENT: Running");
			while (true) {
				//Creates a conecction with the remote server
				Socket client = new Socket(this.ip, port); // socket with the server ip and port

				List<String> filenames = dm.getAvailableFiles();

				//byte[] data = PackageBuilder.buildControlPackage(filenames);
				byte[] data= PackageBuilder.buildReadPacakge("Prueba");

				// write and read streams from socket
				DataOutputStream out = new DataOutputStream(client.getOutputStream());

				out.write(data); // send to server

				out.flush();
				out.close();

				client.close();

				Thread.sleep(5000);

			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
