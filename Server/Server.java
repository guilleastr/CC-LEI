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

	private int port;

	private List<String> conenctedIPs = new ArrayList<String>();

	public Server(int port) {
		super();
		this.port = port;
	}

	/**
	 *
	 * Starts the server
	 */
	@Override
	public void run() {
		int port = this.port;

		System.out.println("Listening on port " + port);
		boolean running = true;
		try {
			DatagramSocket ds = new DatagramSocket(port);
			while (running) { // bad partice - inifinite loop
				// one thread by client

				// createConnection(s);
				DatagramPacket dp = new DatagramPacket(new byte[PackageBuilder.MAX_PACKAGE_SIZE],
						PackageBuilder.MAX_PACKAGE_SIZE);
				ds.receive(dp);

				ClientHandler ch = new ClientHandler(dp); // socket.accept - listens to clients and
															// accepts the connection returning a new socket
				Thread t = new Thread(ch);
				t.start();
			}
			ds.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void createConnection(Socket s) throws IOException {
		String ip = ((InetSocketAddress) s.getRemoteSocketAddress()).getAddress().getHostAddress();
		if (!conenctedIPs.contains(ip)) {
			conenctedIPs.add(ip);
			ControlClient cc = new ControlClient(ip, s.getPort());
			System.out.println("New ControlClient Created!");
			System.out.println("New Connection: " + ip + ":" + s.getPort());
			Thread t = new Thread(cc);
			t.start();
		}
	}

}