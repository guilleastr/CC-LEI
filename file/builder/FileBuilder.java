package file.builder;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import file.DirectoryManagerSingleton;

public class FileBuilder {

	public static final int LAST = -1;

	private List<byte[]> received;
	private String fileName;

	public FileBuilder(String fileName) {
		received = new ArrayList<>();
		this.fileName = fileName;
	}

	public void addReceived(int position, byte[] data) {
		if (position == LAST) {
			received.add(data);
		} else {

		
			received.add(position, data);
		}
	}

	public void buildFile() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (byte[] array : received) {
			try {
				baos.write(array);
				System.out.print("BLOCK");
				System.out.println(Arrays.toString(array));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] total = baos.toByteArray();
		System.out.println(Arrays.toString(total));

		String path = DirectoryManagerSingleton.getInstance().getFullPath() ;
		File outputFile = new File(path + fileName);

		try (FileOutputStream fos = new FileOutputStream(path+"/"+fileName)) {
			outputFile.createNewFile();
			fos.write(total);
			fos.flush();

		System.out.println("FILE SAVED! ");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}
}