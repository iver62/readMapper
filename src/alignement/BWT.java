package alignement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BWT {
	
	private String genome; // le genome a indexer
	private int n; // la taille du genome
	public char[] F, L;
	public int[] suffixArray; // la table des suffixes
	public Map<Character, Integer> map, first;
	public int[] ranks;
	
	public BWT(String genome) {
		this.genome = genome;
		n = genome.length();
		L = new char[n];
		F = new char[n];
		suffixArray = new int[n];
		map = new HashMap<Character, Integer>();
		first = new HashMap<Character, Integer>();
		ranks = new int[n];
	}

	public void transform() {
		for (int i = 0; i < n; i++) {
			F[i] = genome.charAt(suffixArray[i]);
			L[i] = (suffixArray[i]-1 >= 0) ? (genome.charAt(suffixArray[i]-1)) : genome.charAt(n-1);
		}
	}
	
	public void rank() {
		for (int i = 0; i < n; i++) {
			char c = L[i];
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			else {
				map.put(c, map.get(c)+1);
			}
			ranks[i] = map.get(c);
		}
	}
	
	public void firstColumn() {
		for (int i = 0; i < n; i++) {
			int count = map.get(F[i]);
			first.put(F[i], i);
			i += count;
		}
	}
	
	public List<Integer> search(String read) {
		List<Integer> pos = new ArrayList<Integer>();
		int l = read.length();
		char last = read.charAt(l-1);
		int deb = first.get(last), end = deb + map.get(last);
		
		for (int i = deb; i <= end; i++) {
			
			int j = i;
			while (l > 1) {
				char prev = read.charAt(l-2);
				if (L[j] == prev) {
					j = ranks[j] + first.get(prev);
					l--;
				}
				else {
					break;
				}
			}
			
			if (l == 1) {
				pos.add(suffixArray[j]);
			}
			
			l = read.length();
			
		}
		
		return pos;
	}
	
	public int[] encode() {
		int[] enc = new int[n];
		String bwt = new String(L);
		ArrayList<Character> alpha = alphabet();
		for (int i = 0; i < bwt.length(); i++) {
			int ind = alpha.indexOf(bwt.charAt(i));
			enc[i] = ind;
			alpha.remove(ind);
			alpha.add(0, bwt.charAt(i));
		}
		return enc;
	}
	
//	public String decode() {
//		String res = new String();
//		int[] sa = suffixArray();
//		char[] t = transform();
//		return res;
//	}
	
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

//	private String[] rotate() {
//		String[] rotations = new String[n];
//		for (int i = 0; i < n; i++) {
//			rotations[i] = genome.substring(n-i, n) + genome.substring(0, n-i);
//		}
//		return rotations;
//	}

	/**
	 * @return un tableau d'entiers representant la table des suffixes
	 */
	public void suffixArray() {
		String[] suffixes = new String[n];
		for (int i = 0; i < n; i++) {
			suffixes[i] = genome.substring(i);
		}
		Arrays.sort(suffixes);
		for (int i = 0; i < n; i++) {
			suffixArray[i] = genome.indexOf(suffixes[i]);
		}
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
	
	public void printTable(char[] tab) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i]);
		}
		System.out.println();
	}

}
