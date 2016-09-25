package alignement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BWT {
	
	private String genome;
	private int n;
	
	public BWT(String genome) {
		this.genome = genome;
		n = genome.length();
	}

	public String transform() {
		char[] res = new char[n];
		String[] rotations = rotate();
		Arrays.sort(rotations);
		for (int i = 0; i < n; i++) {
			res[i] = rotations[i].charAt(n-1);
		}
		return new String(res);
	}
	
	public int[] encode() {
		int[] enc = new int[n];
		String bwt = transform();
		ArrayList<Character> alpha = alphabet();
		for (int i = 0; i < bwt.length(); i++) {
			int ind = alpha.indexOf(bwt.charAt(i));
			enc[i] = ind;
			alpha.remove(ind);
			alpha.add(0, bwt.charAt(i));
		}
		return enc;
	}
	
	public String decode() {
		String res = new String();
//		int[] sa = suffixArray();
//		char[] t = transform();
		
		return res;
	}
	
	public ArrayList<Character> alphabet() {
		ArrayList<Character> alpha = new ArrayList<Character>();
		for (int i = 0; i < genome.length(); i++) {
			char c = genome.charAt(i);
			if (!alpha.contains(c)) {
				alpha.add(c); 
			}
		}
		Collections.sort(alpha);
		return alpha;
	}

	private String[] rotate() {
		String[] rotations = new String[n];
		for (int i = 0; i < n; i++) {
			rotations[i] = genome.substring(n-i, n) + genome.substring(0, n-i);
		}
		return rotations;
	}

	/**
	 * 
	 * @return un tableau d'entiers representant la table des suffixes
	 */
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
	
//	public void printTable(String[] tab) {
//		for (int i = 0; i < tab.length; i++) {
//			System.out.println(tab[i]);
//		}
//		System.out.println();
//	}
	
	public void printTable(int[] tab) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
		System.out.println();
	}
	
//	public void printTable(char[] tab) {
//		for (int i = 0; i < tab.length; i++) {
//			System.out.print(tab[i] + " ");
//		}
//		System.out.println();
//	}
//	
//	public void printTable(byte[] tab) {
//		for (int i = 0; i < tab.length; i++) {
//			System.out.print(tab[i] + " ");
//		}
//		System.out.println();
//	}

}
