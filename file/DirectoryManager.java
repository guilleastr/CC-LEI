package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryManager {

	private String FULL_PATH;

	public DirectoryManager(String fULL_PATH) {
		super();
		FULL_PATH = fULL_PATH;
	}

	public List<String> getAvailableFiles() {
		File folder = new File(this.FULL_PATH);
		List<String> filenames = new ArrayList<>();

		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isFile()) {
				filenames.add(fileEntry.getName());
			}

		}
		return filenames;
	}

	public List<CustomFile> generateCustomFiles(List<String> files) {
		List<CustomFile> customFiles = new ArrayList<>();

		for (String str : files) {
			customFiles.add(new CustomFile(new File(this.FULL_PATH + "/" + str)));
		}

		return customFiles;
	}

	public File getFile(String filename) {
		return new File(this.FULL_PATH + "/" + filename);
	}

	public String getFullPath() {
		return this.FULL_PATH;
	}

	public List<CustomFile> parseCustomFiles(String string) {
		List<CustomFile> customFiles = new ArrayList<CustomFile>();

		List<String> pairs = Arrays.asList(string.split("\n"));

		for (String pair : pairs) {
			String filename = pair.split(",")[0];
			String md5 = pair.split(",")[1].stripTrailing();
			CustomFile cf = new CustomFile(new File(this.FULL_PATH + "/" + filename));
			cf.setMd5(md5);
			customFiles.add(cf);

		}

		return customFiles;
	}

}
