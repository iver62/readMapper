package alignement;

import java.util.ArrayList;
import java.util.List;

public class Read {
	
	private String sequence;
	private String name;
	
	public Read(String name, String sequence) {
		this.name = name;
		this.sequence = sequence;
	}
	
	public Read(String sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * Renvoie toutes les graines de longueur l
	 * @param l longueur d'un graine
	 * @return la liste de toutes les graines
	 */
	public List<Seed> seed(int l) {
		List<Seed> seeds = new ArrayList<Seed>();
		for (int i = 0; i < length()-l+1; i++) {
			Seed s = new Seed(sequence.substring(i, i+l), this, i);
			seeds.add(s);
		}
		return seeds;
	}
	
	public int length() {
		return sequence.length();
	}
	
	/**
	 * Renvoie le caractere a l'indice i
	 * @param i l'indice
	 * @return le caractere a l'indice i
	 */
	public char charAt(int i) {
		return sequence.charAt(i);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return sequence;
	}

}
