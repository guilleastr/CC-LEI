package packages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import file.CustomFile;
import file.DirectoryManager;
import file.DirectoryManagerSingleton;

public class PackageBuilder {

	public static final byte CONTROL_TYPE = 1;
	public static final byte READ_TYPE = 2;
	public static final byte WRITE_TYPE = 3;
	public static final byte DATA_TYPE = 4;
	public static final byte ACK_TYPE = 5;
	public static final byte ERROR_TYPE = 6;
	
	public static final int MAX_DATA_FOR_PACKAGE=1300;

	public final static int MAX_PACKAGE_SIZE = 1400;

	/**
	 * Creates a byte[] with the information of the files in the system
	 * (file,name,date)
	 * 
	 * @param filenames
	 * @return
	 * @throws IOException
	 */
	public static byte[] buildControlPackage(List<String> filenames) throws IOException {
		byte type = CONTROL_TYPE;

		String filenames_str = "";

		DirectoryManager dm = DirectoryManagerSingleton.getInstance();

		List<CustomFile> customFiles = dm.generateCustomFiles(filenames);

		for (CustomFile file : customFiles) {
			filenames_str = filenames_str + file.toStringFormat();
		}

		short size = (short) filenames_str.length();
		byte[] size_write = new byte[] { (byte) (size >>> 8), (byte) (size & 0xFF) };

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(type);
		baos.write(size_write);
		baos.write(filenames_str.getBytes());

		byte[] pack = buildPackage(baos.toByteArray());
		checkPackageSize(pack);

		return pack;

	}

	/**
	 * Creates a byte[] with the information of the filename for the request
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] buildReadPacakge(String filename) throws IOException {

		byte type = READ_TYPE;

		byte[] filename_bytes = filename.getBytes();

		short size = (short) filename_bytes.length;
		byte[] size_write = new byte[] { (byte) (size >>> 8), (byte) (size & 0xFF) };

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(type);
		baos.write(size_write);
		baos.write(filename_bytes);

		byte[] pack = buildPackage(baos.toByteArray());
		checkPackageSize(pack);

		return pack;

	}

	/**
	 * Creates a byte[] with the information of the filename for the request
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] buildWritePackage(String filename) throws IOException {
		byte type = WRITE_TYPE;

		byte[] filename_bytes = filename.getBytes();

		short size = (short) filename_bytes.length;
		byte[] size_write = new byte[] { (byte) (size >>> 8), (byte) (size & 0xFF) };
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(type);
		baos.write(size_write);
		baos.write(filename_bytes);

		byte[] pack = buildPackage(baos.toByteArray());
		checkPackageSize(pack);

		return pack;

	}

	/**
	 * Creates a byte[] with the information of the data and segmentation for the
	 * request
	 * 
	 * @param data
	 * @param segmentation
	 * @return
	 * @throws IOException
	 */
	public static byte[] buildDataPacakge(String filename, byte[] data, int segmentation) throws IOException {
		byte type = DATA_TYPE;
		short filename_size=(short) filename.length();
		byte[] filename_size_write = new byte[] { (byte) (filename_size >>> 8), (byte) (filename_size & 0xFF) };
		short size = (short) data.length;
		byte[] size_write = new byte[] { (byte) (size >>> 8), (byte) (size & 0xFF) };
		byte[] seg_write = new byte[] { (byte) (segmentation >>> 8), (byte) (segmentation & 0xFF) };
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(type);
		baos.write(filename_size_write);
		baos.write(filename.getBytes());
		baos.write(size_write);
		baos.write(seg_write);
		baos.write(data);

		byte[] pack = buildPackage(baos.toByteArray());
		checkPackageSize(pack);

		return pack;

	}

	/**
	 * Creates a byte[] with the segmentation and filename for the request
	 * 
	 * @param segmentation
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] buildAcknowledgementPackage(int segmentation, byte answer, String filename) throws IOException {

		byte type = ACK_TYPE;

		short seg = (short) segmentation;
		byte[] seg_write = new byte[] { (byte) (seg >>> 8), (byte) (seg & 0xFF) };

		short size = (short) filename.length();
		byte[] size_write = new byte[] { (byte) (size >>> 8), (byte) (size & 0xFF) };


		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(type);
		baos.write(seg_write);
		baos.write(size_write);
		baos.write(answer);
		baos.write(filename.getBytes());

		byte[] pack = buildPackage(baos.toByteArray());
		checkPackageSize(pack);

		return pack;

	}

	/**
	 * Creates a byte[] with the information of the error with type of error and the
	 * message for the request
	 * 
	 * @param type_error
	 * @param error_msg
	 * @return
	 * @throws IOException
	 */

	//Change this
	public static byte[] buildErrorPackage(byte type_error, String error_msg) throws IOException {
		byte type = ERROR_TYPE;

		byte error_t = (byte) type_error;

		short error_msg_size = (short) error_msg.length();
		byte[] error_msg_size_write = new byte[] { (byte) (error_msg_size >>> 8), (byte) (error_msg_size & 0xFF) };

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(type);
		baos.write(type_error);
		baos.write(error_msg_size_write);
		baos.write(error_msg.getBytes());

		byte[] pack = buildPackage(baos.toByteArray());

		checkPackageSize(pack);
		return pack;

	}

	/**
	 * Checks the size of the final package is less than the maximum allowed size
	 * 
	 * @param bytes
	 */
	private static void checkPackageSize(byte[] bytes) {
		if (bytes.length > MAX_PACKAGE_SIZE) {
			throw new ExceptionInInitializerError("Data Too Big");
		}
	}

	/**
	 * Creates a fixed size byte[] from a byte[]
	 * 
	 * @param bytes
	 * @return
	 */
	private static byte[] buildPackage(byte[] bytes) {
		byte[] pack = new byte[MAX_PACKAGE_SIZE];

		for (int i = 0; i < bytes.length; i++) {
			pack[i] = bytes[i];
		}
		return pack;
	}

}
