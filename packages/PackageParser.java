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



	/**
	 * Returns a Package_Executor implementation for a data array by the type (first byte)
	 * @param bytes
	 * @return
	 */
	public static Package_Executor parsePackage(byte[] bytes) {
		short type = Arrays.copyOfRange(bytes, 0, 1)[0];

		switch (type) {
		case 1:
			return parseControlPackage(bytes,type);
		case 2:
			return parseReadPackage(bytes,type);
		case 3:
			return parseWritePackage(bytes,type);
		case 4:
			return parseDataPackage(bytes,type);
		case 5:
			return parseAckPackage(bytes,type);
		case 6:
			return parseErrorPackage(bytes,type);
		}
		return null;

	}

	/**
	 * Return a ReadPackage for the data processed
	 * @param data
	 * @param type
	 * @return
	 */
	private static Package_Executor parseReadPackage(byte[] data, short type) {

		short size = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, 3)).getShort();
		byte[] bytes = Arrays.copyOfRange(data, 3, 3 + size);

		ReadPackage rp = new ReadPackage(type, size, bytes);
		return rp;

	}

	/**
	 * Return a ErrorPackage for the data processed
	 * @param data
	 * @param type
	 * @return
	 */
	private static Package_Executor parseErrorPackage(byte[] data, short type) {
		short error_n = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, 3)).getShort();
		short msg_Size=ByteBuffer.wrap(Arrays.copyOfRange(data, 3, 5)).getShort();
		byte[] msg= Arrays.copyOfRange(data, 5, 5+msg_Size);
		return new ErrorPackage(type, error_n, msg_Size, msg);
	}

	/**
	 * Return a Acknowledgement for the data processed
	 * @param data
	 * @param type
	 * @return
	 */
	private static Package_Executor parseAckPackage(byte[] data, short type) {
		short segment = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, 3)).getShort();
		short size = ByteBuffer.wrap(Arrays.copyOfRange(data, 3, 5)).getShort();
		short ack = ByteBuffer.wrap(Arrays.copyOfRange(data, 5, 7)).getShort();
		byte[] bytes = Arrays.copyOfRange(data, 7, 7 + size);
		return new AcknowlegementPacakge(type, ack, segment, size, bytes);
	}

	/**
	 * Return a WritePackage for the data processed
	 * @param data
	 * @param type
	 * @return
	 */
	private static Package_Executor parseWritePackage(byte[] data, short type) {
		short size = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, 3)).getShort();
		byte[] bytes = Arrays.copyOfRange(data, 3, 3 + size);
		return new WritePackage(type,size,bytes);
	}

	/**
	 * Return a DataPackage for the data processed
	 * @param data
	 * @param type
	 * @return
	 */
	private static Package_Executor parseDataPackage(byte[] data, short type) {
		short size = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, 3)).getShort();
		short filename_size=ByteBuffer.wrap(Arrays.copyOfRange(data, 3, 5)).getShort();
		byte[] filename=Arrays.copyOfRange(data, 5, 5+filename_size);
		short segment = ByteBuffer.wrap(Arrays.copyOfRange(data, 5+filename_size, 7+filename_size)).getShort();
		byte[] bytes = Arrays.copyOfRange(data, 7+filename_size, 7 + size+filename_size);
		return new DataPackage(type,filename, size, segment, bytes);
	}

	/**
	 * * Return a ControlPackage for the data processed
	 * @param data
	 * @param type
	 * @return
	 */
	private static Package_Executor parseControlPackage(byte[] data, short type) {
		short size = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, 3)).getShort();
		byte[] bytes = Arrays.copyOfRange(data, 3, 3 + size);
		return new ControlPackage(type, size, bytes);
	}

}
