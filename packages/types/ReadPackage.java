package packages.types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import file.DirectoryManagerSingleton;
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

	public List<byte[]> execute()  {
		
		System.out.println("READ #" + " | FileName: " + getParsedName());


		FileManager fm = new FileManager(getParsedName());

		List<byte[]> responses = new ArrayList<>();
		byte result=fm.checkFile();
		
		if (result== 0){
			System.out.println(this.getType());
			System.out.println(getSize());
			System.out.println(new String(getFile_name()).toString());

			byte[] data = DirectoryManagerSingleton.getInstance().getNextBytes(this.getParsedName(), 0,
					PackageBuilder.MAX_DATA_FOR_PACKAGE);
			try {
				responses.add(PackageBuilder.buildDataPacakge(this.getParsedName(), data, 0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				responses.add(PackageBuilder.buildErrorPackage(result, "File not found"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
