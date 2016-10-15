package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alignement.Read;

import java.io.FileReader;

public class MyFileReader {
	
	public static String loadGenome(String path) {
		String res = new String();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String ligne;
			while ((ligne = br.readLine()) != null) {
				if (!ligne.equals("") && !ligne.startsWith(">")) { // on ne lit pas la 1ere ligne ni les lignes vides
					res += ligne;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
            System.err.println(e.getMessage());
        }
		
		return res;
	}
	
	public static List<Read> loadReads(String path) {
		List<Read> reads = new ArrayList<Read>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.equals("") && !line.startsWith(">")) { // on ne lit pas la 1ere ligne ni les lignes vides
					reads.add(new Read(line));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
            System.err.println(e.getMessage());
        }
		
		return reads;
	}

}
