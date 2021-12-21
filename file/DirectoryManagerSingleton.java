package file;

public class DirectoryManagerSingleton {

	private static DirectoryManager instance;

	private static String full_path;

	private DirectoryManagerSingleton() {
	}

	
	/**
	 * Creates or return if exist a instance of the Directory manager
	 * @return
	 */
	public static DirectoryManager getInstance() {
		if (instance == null) {
			instance = new DirectoryManager(full_path);
		}
		return instance;
	}

	/**
	 * Initializes the Directory manager Params
	 * @param fullPath
	 */
	public static void init(String fullPath) {
		instance = new DirectoryManager(fullPath);

	}

}
