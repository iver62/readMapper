package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import alignement.Alignement;

public class MyFileWriter {
	
	public static void writeData(String filename, List<Alignement> aligns) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			for (Alignement al : aligns) {
				bw.write(al.getName() + " position" + al.getPosition() + " :\n");
				bw.write(al.toString());
				bw.newLine();
			}
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
