package packages.types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import file.FileManager;
import packages.PackageBuilder;

public class ReadPackage extends Base_Package implements Package_Executor {

	private short size;
	private byte[] file_name;

	public ReadPackage(short type, short size, byte[] file_name) {
		super(type);
		this.size = size;
		this.file_name = file_name;
	}

	public List<byte[]> execute() throws IOException {

		FileManager fm = new FileManager(getParsedName());

		List<byte[]> responses = new ArrayList<>();
		switch (fm.checkFile()) {
			case 0:
				System.out.println(this.getType());
				System.out.println(getSize());
				System.out.println(new String(getFile_name()).toString());
				responses.add(PackageBuilder.buildAcknowledgementPackage(0, this.getParsedName()));
			case ErrorPackage.FILE_NOT_FOUND:
				responses.add(PackageBuilder.buildErrorPackage(ErrorPackage.FILE_NOT_FOUND, "File not found"));
			case ErrorPackage.FILE_NOT_AVAILABLE:
				responses.add(PackageBuilder.buildErrorPackage(ErrorPackage.FILE_NOT_AVAILABLE, "File not available"));
			case ErrorPackage.NO_PERMISSION:
				responses.add(PackageBuilder.buildErrorPackage(ErrorPackage.NO_PERMISSION, "No permission"));
		}
		return responses;

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
