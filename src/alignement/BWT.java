package alignement;

import java.util.ArrayList;
import java.util.Arrays;

public class BWT {
	
	private String genome;
	private int n;
	
	public BWT(String genome) {
		this.genome = genome;
		n = genome.length();
	}

	public char[] transform() {
		char[] res = new char[n];
		String[] rotations = rotate();
		Arrays.sort(rotations);
		for (int i = 0; i < n; i++) {
			res[i] = rotations[i].charAt(n-1);
		}
		return res;
	}
	
	public byte[] encode() {
		byte[] res = new byte[n];
		char[] bwt = transform();
		ArrayList<Byte> alpha = new ArrayList<Byte>();
		for (int i = 0; i < bwt.length; i++) {
			res[i] = (byte) alpha.indexOf(bwt[i]);
			alpha.remove(res[i]);
			alpha.add(0, (byte) bwt[i]);
		}
		return res;
	}
	
	private char[] alphabet() {
		char[] alpha = new char[256];
		for (int i = 0; i < genome.length(); i++) {
//			genome.
		}
		return null;
	}

	private String[] rotate() {
		String[] rotations = new String[n];
		for (int i = 0; i < n; i++) {
			rotations[i] = genome.substring(n-i, n) + genome.substring(0, n-i);
		}
		return rotations;
	}

	public int[] suffixArray() {
		int[] sa = new int[n];
		String[] suffixes = new String[n];
		for (int i = 0; i < n; i++) {
			suffixes[i] = genome.substring(i);
		}
		Arrays.sort(suffixes);
		for (int i = 0; i < n; i++) {
			sa[i] = genome.indexOf(suffixes[i]);
		}
		return sa;
	}
	
	public void printTable(String[] tab) {
		for (int i = 0; i < tab.length; i++) {
			System.out.println(tab[i]);
		}
		System.out.println();
	}
	
	public void printTable(int[] tab) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
		System.out.println();
	}
	
	public void printTable(char[] tab) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
		System.out.println();
	}

}
