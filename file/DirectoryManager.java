package file;

import java.io.File;
import java.util.ArrayList;
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

		for (String fileEntry : folder.list()) {

			filenames.add(fileEntry);

		}
		return filenames;
	}

}
