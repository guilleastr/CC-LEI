package file.builder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		int i = 0;
		byte[] total = new byte[received.stream().mapToInt(x -> x.length).sum()];
		for (byte[] list : received) {
			System.arraycopy(list, 0, total, i, list.length);
			i += list.length;
		}
		String path = DirectoryManagerSingleton.getInstance().getFullPath() ;
		File outputFile = new File(path + fileName);

		try (FileOutputStream fos = new FileOutputStream(path+fileName)) {
			outputFile.createNewFile();
			fos.write(total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}
}