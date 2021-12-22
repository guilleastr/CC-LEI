package packages.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import file.DirectoryManager;
import file.DirectoryManagerSingleton;
import packages.PackageBuilder;

public class AcknowlegementPacakge extends Base_Package implements Package_Executor {

	private short ackNumber;
	private short segmentation;
	private short size_file_name;
	private byte[] file_name;

	public AcknowlegementPacakge(short type, short ackNR, short seg, short size, byte[] name) {
		super(type);
		this.ackNumber = ackNR;
		this.segmentation = seg;
		this.size_file_name = size;
		this.file_name = name;
	}

	public List<byte[]> execute() {
		List<byte[]> responses = new ArrayList<>();
		System.out.println("ACK #" + getAckNumber() + "| Seg #" + getSegmentation() + " | FileName: "
				+ new String(getFile_name()).toString());
		return null;
	}

	public short getAckNumber() {
		return ackNumber;
	}

	public void setAckNumber(short ackNumber) {
		this.ackNumber = ackNumber;
	}

	public short getSegmentation() {
		return segmentation;
	}

	public void setSegmentation(short segmentation) {
		this.segmentation = segmentation;
	}

	public short getSize_file_name() {
		return size_file_name;
	}

	public void setSize_file_name(short size_file_name) {
		this.size_file_name = size_file_name;
	}

	public byte[] getFile_name() {
		return file_name;
	}

	public void setFile_name(byte[] file_name) {
		this.file_name = file_name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AcknowlegementPacakge that = (AcknowlegementPacakge) o;
		return ackNumber == that.ackNumber && segmentation == that.segmentation && size_file_name == that.size_file_name
				&& Objects.equals(file_name, that.file_name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Type:").append(type).append("ACK#").append(ackNumber).append("Seg#").append(segmentation)
				.append("Name size: ").append(size_file_name).append("File name: ").append(file_name);
		return sb.toString();
	}
}
