package packages.types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import file.DirectoryManager;
import file.DirectoryManagerSingleton;
import packages.PackageBuilder;

public class AcknowlegementPacakge extends Base_Package implements Package_Executor {

	private static final short END_SEGMENTATION = -1;

	private short ackNumber;
	private short segmentation;
	private short size_filename;
	private byte[] filename;

	public AcknowlegementPacakge(short type, short ackNR, short seg, short size, byte[] name) {
		super(type);
		this.ackNumber = ackNR;
		this.segmentation = seg;
		this.size_filename = size;
		this.filename = name;
	}

	public List<byte[]> execute() {

		System.out.println("ACK #" + getAckNumber() + "| Seg #" + getSegmentation() + " | FileName: "
				+ getFileName());

		List<byte[]> responses = new ArrayList<>();
		if (this.segmentation != END_SEGMENTATION) {
			try {

				DirectoryManager dm = DirectoryManagerSingleton.getInstance();
				if (dm.checkCompleted(this.getFileName(), segmentation)) {

					responses.add(PackageBuilder.buildAcknowledgementPackage(END_SEGMENTATION, PackageBuilder.ACK_TYPE,
							this.getFileName()));

				} else if (ackNumber == PackageBuilder.DATA_TYPE) {

					byte[] data = dm.getNextBytes(this.getFileName(), this.segmentation,
							PackageBuilder.MAX_DATA_FOR_PACKAGE);

					responses.add(PackageBuilder.buildDataPacakge(this.getFileName(), data,
							this.segmentation + PackageBuilder.MAX_DATA_FOR_PACKAGE));

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return responses;
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

	public short getSize_filename() {
		return size_filename;
	}

	public void setSize_filename(short size_filename) {
		this.size_filename = size_filename;
	}

	public byte[] getfilename() {
		return filename;
	}

	public void setfilename(byte[] filename) {
		this.filename = filename;
	}

	 public String getFileName() {
	    	return new String(this.filename).toString();
	    }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AcknowlegementPacakge that = (AcknowlegementPacakge) o;
		return ackNumber == that.ackNumber && segmentation == that.segmentation && size_filename == that.size_filename
				&& Objects.equals(filename, that.filename);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Type:").append(type).append("ACK#").append(ackNumber).append("Seg#").append(segmentation)
				.append("Name size: ").append(size_filename).append("File name: ").append(filename);
		return sb.toString();
	}
}
