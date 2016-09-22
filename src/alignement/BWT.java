package alignement;

import java.util.Arrays;

public class BWT {
	
	private String genome;
	private int n;
	
	public BWT(String genome) {
		this.genome = genome;
		n = genome.length();
	}

	public String run() {
		String res = new String();
		String[] rotations = rotate();
		Arrays.sort(rotations);
		for (int i = 0; i < n; i++) {
			res += rotations[i].charAt(n-1);
		}
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
	}
	
	public void printTable(int[] tab) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
	}

}
