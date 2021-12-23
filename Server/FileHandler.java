package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import packages.PackageBuilder;
import packages.PackageParser;
import packages.types.Package_Executor;
import stream.CustomDataOutStream;

public class FileHandler extends Thread{
	private Socket socket;
	private byte[] startPackage;

	public FileHandler(Socket socket, byte[] data) {
		this.socket=socket;
		this.startPackage=data;
		
	}

	public Socket getSocket() {
		return socket;
	}

	@Override
	public void run() {
		
		//TODO STOP AND WAIT MECANISIM

		try {
			// getting write and read streams from socket
			// DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
			DataInputStream in = new DataInputStream(this.socket.getInputStream());

			Package_Executor pe = PackageParser.parsePackage(in.readAllBytes());
			List<byte[]> response = new ArrayList<byte[]>();
			if (pe != null) {
				response = pe.execute();

			} else {
				response.add(PackageBuilder.buildErrorPackage(0, "Could not read package"));
			}
			// DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
//			CustomDataOutStream out = new CustomDataOutStream(this.socket.getOutputStream(),
//					this.socket.getRemoteSocketAddress(), this.socket.getPort());
//			if (response != null) {
//				out.write(response);
//			}
//			in.close();
//
//			out.flush();
//			out.close();

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
