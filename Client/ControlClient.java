package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import file.DirectoryManager;
import file.DirectoryManagerSingleton;
import packages.PackageBuilder;

public class ControlClient implements Runnable {

	private String ip;
	private int port;

	public ControlClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;

	}

	/**
	 * Starts the controlClient
	 */
	@Override
	public void run() {
		System.out.println("connecting to " + this.ip + ":" + this.port);
		try {
			DirectoryManager dm = DirectoryManagerSingleton.getInstance();

			System.out.println("CONTROLCLIENT: Running");
			while (true) {
				// Creates a conecction with the remote server
				DatagramSocket ds = new DatagramSocket();

				List<String> filenames = dm.getAvailableFiles();

				// byte[] data = PackageBuilder.buildControlPackage(filenames);
				byte[] data = PackageBuilder.buildReadPacakge("fichero.txt");

				// write and read streams from socket
				DatagramPacket dp = new DatagramPacket(data, PackageBuilder.MAX_PACKAGE_SIZE,
						InetAddress.getByName(this.ip), this.port);
				ds.send(dp);

				ds.close();

				Thread.sleep(100000);

			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
