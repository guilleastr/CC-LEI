package packages.types;

import java.util.ArrayList;
import java.util.List;

import packages.PackageBuilder;

public class WritePackage extends Base_Package implements Package_Executor {

	private short size;
	private byte[] file_name;

	public WritePackage(short type, short size, byte[] file_name) {
		super(type);
		this.size = size;
		this.file_name = file_name;
	}

	public List<byte[]> execute() {
		
		List<byte[]> responses = new ArrayList<>();
		System.out.println(this.getType());
		System.out.println(getSize());
		System.out.println(new String(getFile_name()).toString());
		
		return null;

	}

	public short getSize() {
		return size;
	}

	public byte[] getFile_name() {
		return file_name;
	}

	/**
	 * @return int return the type
	 */
	public int getType() {
		return super.type;
	}

}
