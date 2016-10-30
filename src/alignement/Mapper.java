package alignement;

import java.util.ArrayList;
import java.util.List;

import utils.MyFileWriter;

public class Mapper {
	
	private String genome;
	private List<Read> reads;
	private BurrowsWheelerStructure bws;
	private KBand kb;
	
	public Mapper(String genome, List<Read> reads) {
		this.genome = genome;
		this.reads = reads;		
	}
	
	public void init() {
		bws = new BurrowsWheelerStructure(genome);
		bws.init();
	}
	
	/**
	 * Renvoie toutes les graines de longueur l qui matchent le genome
	 * @param read la sequence a laquelle les graines appartiennent
	 * @param l la longueur des graines
	 * @return une liste de graines
	 */
	public List<Seed> SearchSeeds(Read r, int l) {
		List<Seed> matchedSeeds = new ArrayList<Seed>(); // liste des graines qui matchent le genome
		List<Seed> seeds = r.seed(l); // liste des graines du read
		for (Seed s : seeds) {
			if (bws.find(s).size() > 0) { // si la graine se trouve a au moins une position dans le genome
				matchedSeeds.add(s);
			}
		}
		return matchedSeeds;
	}
	
	/**
	 * Algorithme d'extension (par programmation dynamique) sur les occurences d'une graine 
	 * @param seed la graine
	 * @param match cout d'un match
	 * @param sub cout d'une substitution
	 * @param gap cout d'une insertion/deletion
	 * @param t le taux d'erreurs acceptees
	 * @return le meilleur alignement concernant une graine
	 */
	public Alignement extend(Seed seed, int match, int sub, int gap, double t) {
		List<Alignement> aligns = new ArrayList<Alignement>();
		Read r = seed.getRead(); // le read auquel la graine appartient
		for (Integer i : seed.getPositions()) { // pour chaque position de la graine dans le genome
			int pos = i - seed.getPosition(); // la position dans le genome ou l'alignement commence
			if (i + r.length() <= genome.length()) {
				String genomeSequence = genome.substring(pos, i+r.length()); // la sequence genomique a aligner
				kb = new KBand(genomeSequence, r, match, sub, gap, (int)(r.length()*t));
				kb.buildSimMatrix(); // construction de la matrice de programmation dynamique
				Alignement align = kb.backtrace();
				align.setPosition(pos);
				aligns.add(align);
			}
		}
		Alignement best = bestAlign(aligns); // on ne garde que le meilleur alignement
		if (best != null && best.getProp() < t*100) {
			return best;
		}
		return null;
	}
	
	/**
	 * Renvoie le meilleur alignement parmi une liste d'alignements
	 * @param aligns une liste d'alignements
	 * @return le meilleur alignement
	 */
	private Alignement bestAlign(List<Alignement> aligns) {
		if (aligns.size() > 0) {
			Alignement best = aligns.get(0);
			for (Alignement align : aligns) {
				if (align.getScore() > best.getScore()) {
					best = align;
				}
			}
			return best;
		}
		return null;
	}

	/**
	 * Lance l'algorithme principal
	 * @param l longueur d'une graine
	 * @param match cout d'un match
	 * @param sub cout d'une substitution
	 * @param gap cout d'une insertion/deletion
	 * @param p le taux d'erreurs acceptees
	 */
	public void run(int l, int match, int sub, int gap, double t) {
		List<Alignement> res = new ArrayList<Alignement>();
		for (Read r : reads) { // pour chaque read a aligner
			List<Alignement> aligns = new ArrayList<Alignement>();
			List<Seed> seeds = SearchSeeds(r, l); // la liste de toutes les graines de longueur l qui matchent le genome
			for (Seed s : seeds) { // pour chaque graine
				Alignement align = extend(s, match, sub, gap, t); // alignement avec le genome
				if (align != null) {
					aligns.add(align);
				}
			}
			Alignement best = bestAlign(aligns); // on ne garde que le meilleur alignement
			if (best != null) {
				res.add(best);
			}
		}
		
		System.out.println((double)res.size()/(double)reads.size() * 100 + "% de sequences alignees");
		MyFileWriter.writeData("data/results.txt", res);
	}

}
