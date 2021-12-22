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

	/**
	 * Returns a list (String file names) of the files in the system
	 * 
	 * @return
	 */
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

	/**
	 * Process the customFiles from a list of filenames.
	 * 
	 * @param files
	 * @return List of CustomFile from the filename
	 */
	public List<CustomFile> generateCustomFiles(List<String> files) {
		List<CustomFile> customFiles = new ArrayList<>();

		for (String str : files) {
			customFiles.add(new CustomFile(new File(this.FULL_PATH + "/" + str)));
		}

		return customFiles;
	}

	/**
	 * Returns a object File from a filename
	 * 
	 * @param filename
	 * @return
	 */
	public File getFile(String filename) {
		return new File(this.FULL_PATH + "/" + filename);
	}

	/**
	 * Return fullpath of the DirectoryManager
	 * 
	 * @return
	 */
	public String getFullPath() {
		return this.FULL_PATH;
	}

	/**
	 * Process a String into a List of CustomFile=>(filename,hasmd5,date\n)
	 * 
	 * @param string
	 * @return
	 */
	public List<CustomFile> parseCustomFiles(String string) {
		List<CustomFile> customFiles = new ArrayList<CustomFile>();

		List<String> pairs = Arrays.asList(string.split("\n"));

		for (String pair : pairs) {

			customFiles.add(new CustomFile(pair));

		}

		return customFiles;
	}

	public byte[] getRange(byte[] mainFile, int sizeOfChunk, int numChunk) {
		byte[] range;
		if((numChunk+1)*sizeOfChunk > mainFile.length){
			range = Arrays.copyOfRange(mainFile,numChunk*sizeOfChunk, mainFile.length);
		} else {
			range = Arrays.copyOfRange(mainFile,numChunk*sizeOfChunk, (numChunk+1)*sizeOfChunk);
		}
		return range;
	}

}
