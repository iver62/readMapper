package alignement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BurrowsWheelerStructure {
	
	private String genome; // le genome a indexer
	private int n; // la taille du genome
	public char[] F, L; // les colonnes last et first, last correspond a la transformee de Burrows-Wheeler
	public int[] suffixArray; // la table des suffixes
	public Map<Character, Integer> map, first;
	public int[] ranks;
	
	public BurrowsWheelerStructure(String genome) {
		this.genome = genome;
		n = genome.length();
		L = new char[n];
		F = new char[n];
		suffixArray = new int[n];
		map = new HashMap<Character, Integer>();
		first = new HashMap<Character, Integer>();
		ranks = new int[n];
	}
	
	/**
	 * Initialise la structure de Burrows-Wheeler
	 */
	public void init() {
		suffixArray(); // construction de la table des suffixes
		transform(); // construction des colonnes last et first
		rank(); // construction de la table des rangs
		firstColumn();
	}

	/**
	 * Calcule la transformee de Burrows-Wheeler
	 */
	public void transform() {
		for (int i = 0; i < n; i++) {
			F[i] = genome.charAt(suffixArray[i]);
			L[i] = (suffixArray[i]-1 >= 0) ? (genome.charAt(suffixArray[i]-1)) : genome.charAt(n-1);
		}
	}
	
	/**
	 * 
	 */
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
	
	/**
	 * Cherche a quelles positions la graine matche le genome
	 * @param seed la graine a chercher
	 * @return une liste de positions
	 */
	public List<Integer> find(Seed seed) {
		List<Integer> pos = new ArrayList<Integer>(); // liste des positions ou la graine matche
		int l = seed.length();
		char last = seed.charAt(l-1); // dernier caractere de la graine
		
		if (!map.containsKey(last)) { // si la derniere lettre n'est pas dans l'alphabet du genome
			return pos;
		}
		int deb = first.get(last), end = deb + map.get(last); //l'intervalle de la 1ere colonne contenant la derniere lettre de la graine 
		
		for (int i = deb; i <= end; i++) { 
			int j = i; // le caractere courant
			while (l > 1) { // tant que tous les caracteres de la graine ont ete trouves
				char prev = seed.charAt(l-2);
				if (!map.containsKey(last)) {
					return pos;
				}
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
			
			l = seed.length();	
		}
		
		Collections.sort(pos);
		seed.setPositions(pos);
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

	/**
	 * Construit la table des suffixes
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
