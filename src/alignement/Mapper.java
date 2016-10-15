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
	
	public Alignement extend(Seed seed, int match, int sub, int gap,/* int k,*/ double p) {
		List<Alignement> aligns = new ArrayList<Alignement>();
		Read r = seed.getRead(); // le read auquel la graine appartient
		for (Integer i : seed.getPositions()) { // pour chaque position de la graine dans le genome
			int pos = i - seed.getPosition(); // la position dans le genome ou l'alignement commence
			if (i + r.length() <= genome.length()) {
				String genomeSequence = genome.substring(pos, i+r.length());
				kb = new KBand(genomeSequence, r, match, sub, gap, (int)(r.length()*p));
				kb.buildSimMatrix();
				Alignement align = kb.backtrace();
				align.setPosition(pos);
//				System.out.println(align);
				aligns.add(align);
			}
		}
		Alignement best = bestAlign(aligns);
		if (best.getProp() < p*100) {
//			System.out.println("best : \n" + best);
			return best;
		}
		return null;
	}
	
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

	public void run(int l, int match, int sub, int gap,/* int k,*/ double p) {
		List<Alignement> res = new ArrayList<Alignement>();
		int cpt = 1;
		for (Read r : reads) { // pour chaque read a aligner
			System.out.println(cpt++);
			List<Alignement> aligns = new ArrayList<Alignement>();
			List<Seed> seeds = SearchSeeds(r, l); // la liste de toutes les graines de longueur l qui matchent le genome
			for (Seed s : seeds) {
				Alignement align = extend(s, match, sub, gap, /*k,*/ p);
				if (align != null) {
					aligns.add(align);
				}
			}
			Alignement best = bestAlign(aligns);
			if (best != null) {
				res.add(best);
			}
		}
		
		System.out.println((double)res.size()/(double)reads.size() * 100 + "% de sequences alignees");
		MyFileWriter.writeData("results.txt", res);
	}

}