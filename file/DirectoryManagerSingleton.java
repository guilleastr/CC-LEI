package file;

public class DirectoryManagerSingleton {

	private static DirectoryManager instance;

	private static String full_path;

	private DirectoryManagerSingleton() {
	}

	public static DirectoryManager getInstance() {
		if (instance == null) {
			instance = new DirectoryManager(full_path);
		}
		return instance;
	}

	public static void init(String fullPath) {
		instance = new DirectoryManager(fullPath);

	}

}
