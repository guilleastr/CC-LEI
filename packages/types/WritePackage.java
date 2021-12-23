package packages.types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import file.FileManager;
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
		System.out.println("WRITE #" +  " | FileName: " + getParsedName());

		FileManager fm = new FileManager(getParsedName());

		List<byte[]> responses = new ArrayList<>();
		byte result=fm.checkFile();
		
		if (result== 0){
			System.out.println(this.getType());
			System.out.println(getSize());
			System.out.println(new String(getFile_name()).toString());
			try {
				responses.add(PackageBuilder.buildAcknowledgementPackage(0, PackageBuilder.WRITE_TYPE, this.getParsedName()));
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
	
	private String getParsedName() {
		return new String(getFile_name()).toString();
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
