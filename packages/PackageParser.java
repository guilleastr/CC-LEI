package packages;

import java.nio.ByteBuffer;
import java.util.Arrays;

import packages.types.AcknowlegementPacakge;
import packages.types.ControlPackage;
import packages.types.DataPackage;
import packages.types.ErrorPackage;
import packages.types.Package_Executor;
import packages.types.ReadPackage;
import packages.types.WritePackage;

public class PackageParser {

	public short type;
	public short size;
	public byte[] bytes;

	public Package_Executor parsePackage(byte[] bytes) {
		this.type = Arrays.copyOfRange(bytes, 0, 1)[0];

		switch (type) {
		case 1:
			return parseControlPackage(bytes);
		case 2:
			return parseReadPackage(bytes);
		case 3:
			return parseWritePackage(bytes);
		case 4:
			return parseDataPackage(bytes);
		case 5:
			return parseAckPackage(bytes);
		case 6:
			return parseErrorPackage(bytes);
		}
		return null;

	}

	private Package_Executor parseReadPackage(byte[] bytes2) {

		this.size = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 1, 3)).getShort();
		this.bytes = Arrays.copyOfRange(bytes2, 3, 3 + this.size);

		ReadPackage rp = new ReadPackage(type, size, this.bytes);
		return rp;

	}

	private Package_Executor parseErrorPackage(byte[] bytes2) {
		short error_n = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 1, 3)).getShort();
		short msg_Size=ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 3, 5)).getShort();
		byte[] msg= Arrays.copyOfRange(bytes2, 5, 5+msg_Size);
		return new ErrorPackage(this.type, error_n, msg_Size, msg);
	}

	private Package_Executor parseAckPackage(byte[] bytes2) {
		short segment = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 1, 3)).getShort();
		this.size = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 3, 5)).getShort();
		short ack = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 5, 7)).getShort();
		this.bytes = Arrays.copyOfRange(bytes2, 7, 7 + this.size);
		return new AcknowlegementPacakge(type, ack, segment, this.size, this.bytes);
	}

	private Package_Executor parseWritePackage(byte[] bytes2) {
		this.size = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 1, 3)).getShort();
		this.bytes = Arrays.copyOfRange(bytes2, 3, 3 + size);
		return new WritePackage(type,size,bytes);
	}

	private Package_Executor parseDataPackage(byte[] bytes2) {
		this.size = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 1, 3)).getShort();
		short segment = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 3, 5)).getShort();
		this.bytes = Arrays.copyOfRange(bytes2, 5, 5 + this.size);
		return new DataPackage(type, size, segment, bytes);
	}

	private Package_Executor parseControlPackage(byte[] bytes2) {
		this.size = ByteBuffer.wrap(Arrays.copyOfRange(bytes2, 1, 3)).getShort();
		this.bytes = Arrays.copyOfRange(bytes2, 3, 3 + size);
		return new ControlPackage(type, size, bytes);
	}

}
