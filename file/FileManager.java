package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import packages.types.ErrorPackage;

public class FileManager {

	private String filename;

	private byte[] data;

	public FileManager(String filename) {
		super();
		this.filename = filename;
		try {
			openFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void openFile() throws IOException {

		String full_path = DirectoryManagerSingleton.getInstance().getFullPath();

		File file = new File(full_path + "\\" + this.filename);
		this.data = new byte[(int) file.length()];

		FileInputStream fileInputStream = new FileInputStream(file);
		fileInputStream.read(data);
		fileInputStream.close();

	}

	public byte checkFile() {
        String full_path = DirectoryManagerSingleton.getInstance().getFullPath();
        File f = new File(full_path + "\\" + filename);
        byte ret = 0;
        if(!f.isFile())
            ret = ErrorPackage.FILE_NOT_AVAILABLE;
        if(!f.exists())
            ret = ErrorPackage.FILE_NOT_FOUND;
        if(!f.canRead() || !f.canWrite())
            ret = ErrorPackage.NO_PERMISSION;

        return ret;
    }
	
	public int getDataLength() {
		return data.length;
	}

	public byte[] getBytes(int start, int end) {

		return Arrays.copyOfRange(data, start, end);
	}



}
