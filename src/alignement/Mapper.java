package alignement;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
	
	private String genome;
	private List<Read> reads;
	private BurrowsWheelerStructure bws;
	private KBand kb;
//	private int match, sub, gap; // les scores
//	private int k;
	
	public Mapper(String genome, List<Read> reads/*, int match, int sub, int gap, int k*/) {
		this.genome = genome;
		this.reads = reads;
//		this.match = match;
//		this.sub = sub;
//		this.gap = gap;
//		this.k = k;
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
	
	public void extend(Seed seed, int match, int sub, int gap, int k, int p) {
		List<Alignement> aligns = new ArrayList<Alignement>();
		Read r = seed.getRead();
		for (Integer i : seed.getPositions()) {
			if (i + r.length() < genome.length()) {
				String genomeSequence = genome.substring(i-seed.getPosition(), i+r.length());
				kb = new KBand(genomeSequence, r, match, sub, gap, k);
				kb.buildSimMatrix();
				Alignement align = kb.backtrace();
				aligns.add(align);
//				System.out.println(align);
			}
		}
		Alignement best = bestAlign(aligns);
		if (best.getProp() <= p) {
			System.out.println("meilleur alignement = \n" + best);
		}
//		return kb.backtrace();
	}
	
	private Alignement bestAlign(List<Alignement> aligns) {
		Alignement best = aligns.get(0);
		for (Alignement align : aligns) {
			if (align.getScore() > best.getScore()) {
				best = align;
			}
		}
		return best;
	}

	public void run(int l, int match, int sub, int gap, int k, int p) {
		for (Read r : reads) { // pour chaque read a aligner
			List<Seed> seeds = SearchSeeds(r, l); // la liste de toutes les graines de longueur l qui matchent le genome
			for (Seed s : seeds) {
				extend(s, match, sub, gap, k, p);
			}
		}
	}

//	public String getGenome() {
//		return genome;
//	}

//	public Read getRead() {
//		return read;
//	}
//
//	public BurrowsWheelerStructure getBws() {
//		return bws;
//	}
//
//	public KBand getKb() {
//		return kb;
//	}

}
