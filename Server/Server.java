package Server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import Client.ControlClient;

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
			ServerSocket ssocket = new ServerSocket(port);
			while (running) { // bad partice - inifinite loop
				// one thread by client
				Socket s = ssocket.accept();

				//createConnection(s);

				System.out.println("SERVER: Request recieved");
				ClientHandler ch = new ClientHandler(s); // socket.accept - listens to clients and
															// accepts the connection returning a new socket
				Thread t = new Thread(ch);
				t.start();
			}
			ssocket.close();
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