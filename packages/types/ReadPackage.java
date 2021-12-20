package packages.types;

import java.io.IOException;
import java.util.Objects;

import file.FileManager;
import packages.PackageBuilder;
import packages.PackageParser;

public class ReadPackage extends Base_Package {

	private short size;
	private byte[] file_name;

	private PackageBuilder pb = new PackageBuilder();

	public ReadPackage(int type, short size, byte[] file_name) {
		super(type);
		this.size = size;
		this.file_name = file_name;
	}

	@Override
	public byte[] execute() throws IOException {

		FileManager fm = new FileManager(getParsedName());

		if (fm.checkFile()) {
			System.out.println(this.getType());
			System.out.println(getSize());
			System.out.println(new String(getFile_name()).toString());

			return pb.buildAcknowledgementPackage(0, this.getParsedName());

		}

		return pb.buildErrorPackage(1, "file not available");

	}

	/**
	 * @return int return the type
	 */
	public int getType() {
		return super.type;
	}

	/**
	 * @return int return the size
	 */
	public short getSize() {
		return size;
	}

	public byte[] getFile_name() {
		return file_name;
	}

	public void setFile_name(byte[] file_name) {
		this.file_name = file_name;
	}

	private String getParsedName() {
		return new String(getFile_name()).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ReadPackage that = (ReadPackage) o;
		return size == that.size && Objects.equals(file_name, that.file_name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Size: ").append(size).append(" | File name: ").append(file_name);
		return sb.toString();
	}
}
