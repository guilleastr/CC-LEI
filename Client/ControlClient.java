package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import file.DirectoryManager;
import packages.PackageBuilder;

public class ControlClient implements Runnable {

	private String ip;
	private int port;

	private DirectoryManager dm;

	public ControlClient(String ip, int port, DirectoryManager dm) {
		super();
		this.ip = ip;
		this.port = port;
		this.dm = dm;
	}

	@Override
	public void run() {
		System.out.println("connecting to " + this.ip + ":" + this.port);
		try {

			while (true) {
				Socket client = new Socket(this.ip, port); // socket with the server ip and port

				System.out.println("Checking files:");

				List<String> filenames = dm.getAvailableFiles();

				PackageBuilder pb = new PackageBuilder();
				byte[] data = pb.buildControlPackage(filenames);

				// write and read streams from socket
				DataOutputStream out = new DataOutputStream(client.getOutputStream());

				out.write(data); // send to server
				System.out.println("Data Sent");

				out.flush();
				out.close();

				client.close();

				Thread.sleep(10000);

			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
