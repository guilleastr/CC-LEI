package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomFile {

	private File file;

	private String md5;

	public CustomFile(File file) {
		super();
		this.file = file;
		generateMD5();
	}

	private void generateMD5() {
		MessageDigest md;
		byte[] messageDigest = null;
		try {
			md = MessageDigest.getInstance("MD5");
			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			FileInputStream fis=new FileInputStream(file);
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

	private File getFile() {
		return file;
	}

	private String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Override
	public String toString() {
		return file.getName() + "," + md5 ;
	}
	
	public String toStringFormat() {
		return file.getName() + "," + md5 + "\n";
	}

	

}
