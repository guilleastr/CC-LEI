
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import file.DirectoryManagerSingleton;
import packages.PackageBuilder;
import packages.PackageParser;

public class Probador {

	public static void main(String[] args) {

		try {
			//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			// byte[] data = pb.buildControlPackage(Arrays.asList("Fichero1.txt",
			// "fichero2.txt"));
			// //byte[] data=pb.buildWritePackage("fichero.txt");
			// byte[] data=pb.buildReadPacakge("Hola Mundo");
			// byte[] info="Hola Mundo".getBytes();
			// byte[] data=pb.buildDataPacakge(info, 0);
			// byte[] data=pb.buildAcknowledgementPackage(0, "fichero1.txt");
			// byte[] data=pb.buildErrorPackage(1,"Fichero no disponible");

			// pp.parsePackage(data).execute();

			DirectoryManagerSingleton.getInstance();
			DirectoryManagerSingleton.init("C:/Users/LENOVO/Desktop/Erasmus/CC/Practica/TP02/check");

			byte[] data = PackageBuilder.buildControlPackage(DirectoryManagerSingleton.getInstance().getAvailableFiles());

			PackageParser.parsePackage(data).execute();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}