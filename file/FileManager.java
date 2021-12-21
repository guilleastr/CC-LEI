package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileManager {

	private String filename;

	private byte[] data;

	public FileManager(String filename) {
		super();
		this.filename = filename;
	}


	public void openFile() throws IOException {

		String full_path = DirectoryManagerSingleton.getInstance().getFullPath();

		File file = new File(full_path + "\\" + this.filename);
		this.data = new byte[(int) file.length()];

		FileInputStream fileInputStream = new FileInputStream(file);
		fileInputStream.read(data);

		fileInputStream.close();

	}

	public boolean checkFile() {
		String full_path = DirectoryManagerSingleton.getInstance().getFullPath();
		File f = new File(full_path + "\\" + filename);
		return f.isFile() && f.exists() && f.canRead() && f.canWrite();

	}

	public byte[] getBytes(int start, int end) {

		return Arrays.copyOfRange(data, start, end);
	}



}
