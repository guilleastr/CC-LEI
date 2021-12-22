package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Abstraction of File. For easly calculatin MD5 hash and Date.
 *
 */
public class CustomFile {

	private File file;

	private String md5;
	private long time;

	public CustomFile(File file) {
		super();
		this.file = file;
		generateMD5();
		this.time = file.lastModified();

	}

	public CustomFile(String string) {
		String filename = string.split(",")[0];
		this.file = DirectoryManagerSingleton.getInstance().getFile(filename);
		this.md5 = string.split(",")[1].split("/")[0].stripTrailing();
		this.time = Long.parseLong(string.split(",")[1].split("/")[1].stripTrailing());
	}

	/**
	 * Generates a MD5 hash and sets it to a class variable
	 */
	private void generateMD5() {
		MessageDigest md;
		byte[] messageDigest = null;
		try {
			md = MessageDigest.getInstance("MD5");
			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			FileInputStream fis = new FileInputStream(file);
			messageDigest = md.digest(fis.readAllBytes());
			fis.close();
		} catch (NoSuchAlgorithmException | IOException e) {

			e.printStackTrace();
		}

		// Convert byte array into signum representation
		BigInteger no = new BigInteger(1, messageDigest);

		// Convert message digest into hex value
		String hashtext = no.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		this.md5 = hashtext;

	}
	
	
	
	public String getName() {
		return file.getName();
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	

	public String getMd5() {
		return md5;
	}
	
	

	public long getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "FILE: "+ file.getName() + "; " + md5+"; TIME: "+time;
	}

	/**
	 * Returns the string that will be inserted into a data section package
	 * 
	 * @return
	 */
	public String toStringFormat() {
		return file.getName() + "," + md5 + "/" + file.lastModified() + "\n";
	}

}
