package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import packages.PackageBuilder;

public class FileHandler extends Thread {
	private DatagramSocket datagramSocket;
	private DatagramPacket send;

	public FileHandler(DatagramPacket send, DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
		this.send = send;

	}


	@Override
	public void run() {

		// TODO STOP AND WAIT MECANISIM

		try {
			datagramSocket.send(send);
			
			byte[] data= new byte[PackageBuilder.MAX_PACKAGE_SIZE];
			DatagramPacket recieved= new DatagramPacket(data, PackageBuilder.MAX_PACKAGE_SIZE);
			datagramSocket.receive(recieved);
			System.out.println("Recieved: "+recieved.getData());
			
			datagramSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
