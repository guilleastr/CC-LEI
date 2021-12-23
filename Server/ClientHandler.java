package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import packages.PackageBuilder;
import packages.PackageParser;
import packages.types.Base_Package;
import packages.types.Package_Executor;
import stream.CustomDatagramPacketManager;

/**
 * Created when a server recieves a request. ClientHandler process the request
 * and sends back a response
 *
 */
public class ClientHandler implements Runnable {
	private DatagramPacket recieved;

	public ClientHandler(DatagramPacket recieved) {
		this.recieved = recieved;
	}

	@Override
	public void run() {

		try {
			

			Package_Executor pe = PackageParser.parsePackage(recieved.getData());

			List<byte[]> response = new ArrayList<byte[]>();
			short type = PackageBuilder.ERROR_TYPE;
			if (pe != null) {
				type = (short) ((Base_Package) pe).getType();
				response = pe.execute();
			} else {
				response.add(PackageBuilder.buildErrorPackage(0, "Could not read package"));
			}

			System.out.println("Package Recieved: " + type);

			CustomDatagramPacketManager out = new CustomDatagramPacketManager(recieved.getAddress(),recieved.getPort());
			if (response != null) {
				out.write(response, type);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
