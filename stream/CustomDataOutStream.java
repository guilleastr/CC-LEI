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

public class CustomDataOutStream extends DataOutputStream {

	private String serverIP;
	private int port;

	public CustomDataOutStream(OutputStream outputStream, String serverIP, int port) {
		super(outputStream);
		this.serverIP = serverIP;
		this.port = port;
	}

	public CustomDataOutStream(OutputStream outputStream, SocketAddress remoteSocketAddress, int port2) {
		super(outputStream);
		this.serverIP = processIP(remoteSocketAddress);
		this.port = port;
	}

	private String processIP(SocketAddress remoteSocketAddress) {
		InetAddress inaddr = ((InetSocketAddress) remoteSocketAddress).getAddress();
		Inet4Address in4addr = (Inet4Address) inaddr;
		byte[] ip4bytes = in4addr.getAddress(); // returns byte[4]
		return in4addr.toString();
	}

	public void write(List<byte[]> dataList) throws IOException {
		if (dataList.size() > 1) {
			Socket client = new Socket(serverIP, port);

		} else {
			super.write(dataList.get(0));

		}
	}

}
