package alignement;

import java.util.Arrays;

public class BWT {
	
	private String genome;
	private int n;
	
	public BWT(String genome) {
		this.genome = genome;
		n = genome.length();
	}

	public char[] run() {
		char[] res = new char[n];
		String[] rotations = rotate();
		Arrays.sort(rotations);
		for (int i = 0; i < n; i++) {
			res[i] = rotations[i].charAt(n-1);
		}
		return res;
	}
	
	public int[] moveToFront() {
		int[] res = new int[n];
		return res;
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
