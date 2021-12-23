package stream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import Server.FileHandler;
import packages.PackageBuilder;

public class CustomDatagramPacketManager {

	private InetAddress ip;
	private int port;

	public CustomDatagramPacketManager(InetAddress ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void write(List<byte[]> dataList) throws IOException {
		if (dataList.size() == 1) {
			DatagramSocket dsUndefined = new DatagramSocket();
			DatagramPacket send = new DatagramPacket(dataList.get(0), PackageBuilder.MAX_PACKAGE_SIZE,this.ip, this.port);
			dsUndefined.send(send);
			System.out.println("Package Return: " + dataList.get(0)[0]);

		}
	}

	public void write(List<byte[]> response, short type) {
		try {
			if (type == PackageBuilder.CONTROL_TYPE) {
				for (byte[] data : response) {
					DatagramPacket send = new DatagramPacket(data, PackageBuilder.MAX_PACKAGE_SIZE, ip, port);
					send.setAddress(ip);
					// System.out.println("Package Return: " + data[0]);
					FileHandler fh = new FileHandler(send, new DatagramSocket());
					// fh.start();
				}
			} else {
				this.write(response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
