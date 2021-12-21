package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import packages.PackageBuilder;
import packages.PackageParser;
import packages.types.Package_Executor;

public class ClientHandler implements Runnable {
	private Socket socket;


	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	@Override
	public void run() {

		try {
			// getting write and read streams from socket
			// DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
			DataInputStream in = new DataInputStream(this.socket.getInputStream());

			Package_Executor pe = PackageParser.parsePackage(in.readAllBytes());
			byte[] response;
			if (pe != null) {
				response = pe.execute();

			} else {
				response = PackageBuilder.buildErrorPackage(0, "Could not read package");
			}
			DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
			if(response!=null) {
				out.write(response);
			}
			in.close();

			out.flush();
			out.close();

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
