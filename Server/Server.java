package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Client.ControlClient;
import packages.PackageBuilder;

/**
 * Small and stupid TCP server
 */
public class Server implements Runnable {

	public final static int PORT = 8081;

	private int port;

	private List<String> conenctedIPs = new ArrayList<String>();

	public Server(int port) {
		super();
		this.port = port;
	}

	public Server() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * Starts the server
	 */
	@Override
	public void run() {
		int port = PORT;

		System.out.println("Listening on port " + PORT);
		boolean running = true;
		try {
			DatagramSocket ds = new DatagramSocket(PORT);
			while (running) { // bad partice - inifinite loop
				// one thread by client

				DatagramPacket dp = new DatagramPacket(new byte[PackageBuilder.MAX_PACKAGE_SIZE],
						PackageBuilder.MAX_PACKAGE_SIZE);
				ds.receive(dp);

				ClientHandler ch = new ClientHandler(dp);
				//createConnection(dp);
				Thread t = new Thread(ch);
				t.start();
			}
			ds.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void createConnection(DatagramPacket dp) throws IOException {
		
		String ip = new String(dp.getAddress().getAddress()).toString();
		if (!conenctedIPs.contains(ip)) {
			conenctedIPs.add(ip);
			ControlClient cc = new ControlClient(ip);
			System.out.println("New ControlClient Created!");
			System.out.println("New Connection: " + ip);
			Thread t = new Thread(cc);
			t.start();
		}
	}

}