package stream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

import Server.FileHandler;
import packages.PackageBuilder;

public class CustomDataOutStream extends DataOutputStream {

	private String serverIP;
	private int port;

	public CustomDataOutStream(OutputStream outputStream, Socket socket) {
		super(outputStream);
		this.serverIP = this.processIP(socket.getRemoteSocketAddress());
		this.port = socket.getPort();
	}



	public void write(List<byte[]> dataList) throws IOException {
		if (dataList.size() == 1) {

			System.out.println("Package Return: "+dataList.get(0)[0]);
			super.write(dataList.get(0));

		}
	}

	public void write(List<byte[]> response, short type) {
		try {
			if (type == PackageBuilder.CONTROL_TYPE) {
				for (byte[] data : response) {
					System.out.println("Package Return: "+data[0]);
					FileHandler fh = new FileHandler(new Socket(serverIP, port), data);
					fh.start();
				}
			} else {
				this.write(response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private String processIP(SocketAddress remoteSocketAddress) {
		InetAddress inaddr = ((InetSocketAddress) remoteSocketAddress).getAddress();
		Inet4Address in4addr = (Inet4Address) inaddr;
		byte[] ip4bytes = in4addr.getAddress(); // returns byte[4]
		return in4addr.toString().substring(1);
	}

}
