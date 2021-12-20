package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileManager {

	private String filename;

	private byte[] data;

	private String full_path="C:\\Users\\LENOVO\\Desktop\\Erasmus\\CC\\Practica\\TP02\\check";

	public FileManager(String filename) {
		super();
		this.filename = filename;
	}

	public void openFile() throws IOException {

		File file = new File(this.full_path + "\\" + this.filename);
		this.data = new byte[(int) file.length()];

		FileInputStream fileInputStream = new FileInputStream(file);
		fileInputStream.read(data);
		
		fileInputStream.close();
		
	}
	
	public boolean checkFile() {
		
		File f = new File(this.full_path + "\\" + filename);
	    return f.isFile()&&f.exists()&&f.canRead()&&f.canWrite();
	    
	}

	public byte[] getBytes(int start, int end) {

		return Arrays.copyOfRange(data, start, end);
	}

	private String getFilename() {
		return filename;
	}

	private byte[] getData() {
		return data;
	}

}
