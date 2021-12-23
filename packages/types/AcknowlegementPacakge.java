package packages.types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import file.DirectoryManager;
import file.DirectoryManagerSingleton;
import file.builder.FileBuilderPool;
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

		System.out.println("ACK #" + getAckNumber() + "| Seg #" + getSegmentation() + " | FileName: " + getFileName());

		List<byte[]> responses = new ArrayList<>();
		if (this.ackNumber == PackageBuilder.CONTROL_TYPE) {
			return responses;
		} else if (this.segmentation != END_SEGMENTATION) {
			try {

				DirectoryManager dm = DirectoryManagerSingleton.getInstance();
				if (dm.checkCompleted(this.getFileName(), segmentation * PackageBuilder.MAX_DATA_FOR_PACKAGE)) {

					responses.add(PackageBuilder.buildAcknowledgementPackage(END_SEGMENTATION, PackageBuilder.ACK_TYPE,
							this.getFileName()));

				} else if (ackNumber == PackageBuilder.DATA_TYPE) {

					byte[] data = dm.getNextBytes(this.getFileName(),
							this.segmentation * PackageBuilder.MAX_DATA_FOR_PACKAGE,
							PackageBuilder.MAX_DATA_FOR_PACKAGE);

					short seg = (short) (this.segmentation + 1);

					responses.add(PackageBuilder.buildDataPacakge(this.getFileName(), data, seg));

				} else if (ackNumber == PackageBuilder.WRITE_TYPE) {
					byte[] data = DirectoryManagerSingleton.getInstance().getNextBytes(this.getParsedName(), 0,
							PackageBuilder.MAX_DATA_FOR_PACKAGE);
					try {
						responses.add(PackageBuilder.buildDataPacakge(this.getParsedName(), data, 0));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else {
			System.out.println("FILE: " + this.getFileName() + " TRANSMITIONCOMPLETED");
			FileBuilderPool.save(this.getFileName());
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

	private String getParsedName() {
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
