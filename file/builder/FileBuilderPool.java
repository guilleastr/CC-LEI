package file.builder;

import java.util.ArrayList;
import java.util.List;

public class FileBuilderPool {

	private static List<FileBuilder> filebuilders = new ArrayList<FileBuilder>();

	/**
	 * Creates or return if exist a instance of the Directory manager
	 * 
	 * @return
	 */

	public static void addFileBuilder(String filename) {
		filebuilders.add(new FileBuilder(filename));
	}

	public static void addBytes(String fileName, byte[] data) {
		for (FileBuilder fb : filebuilders) {
			if (fb.getFileName().equals(fileName)) {
				fb.addReceived(FileBuilder.LAST, data);
				return;
			}
		}
		addFileBuilder(fileName);
		addBytes(fileName, data);
	}

	public static void addBytesPos(String fileName, byte[] data, int pos) {
		for (FileBuilder fb : filebuilders) {
			if (fb.getFileName().equals(fileName)) {
				fb.addReceived(pos, data);
				return;
			}
		}
		addFileBuilder(fileName);
		addBytes(fileName, data);
	}

	public static void save(String fileName) {
		FileBuilder fbAUX = new FileBuilder(fileName);
		for (FileBuilder fb : filebuilders) {
			if (fb.getFileName().equals(fileName)) {
				fb.buildFile();
				fbAUX = fb;

				break;
			}
		}

		filebuilders.remove(fbAUX);

	}

}
