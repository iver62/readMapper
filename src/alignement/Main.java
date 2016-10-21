package alignement;

import java.util.List;

import utils.MyFileReader;

public class Main {

	public static void main(String[] args) {
		
		if (args.length == 7) {
			
			String genomPath = args[0];
			String readsPath = args[1];
			int seedLength = Integer.parseInt(args[2]);
			int match = Integer.parseInt(args[3]);
			int sub = Integer.parseInt(args[4]);
			int gap = Integer.parseInt(args[5]);
			double t = Double.parseDouble(args[6]);
			
			String genome = MyFileReader.loadGenome(genomPath);
			genome += "$";
			List<Read> reads = MyFileReader.loadReads(readsPath);
			
			System.out.println("Running...");
			
			Mapper map = new Mapper(genome, reads);
			
			long deb = System.currentTimeMillis();
			map.init();
			long time = System.currentTimeMillis() - deb;
			System.out.println(time + "ms pour l'indexation");
			
			long deb1 = System.currentTimeMillis();
			map.run(seedLength, match, sub, gap, t);
			long time1 = System.currentTimeMillis() - deb1;
			System.out.println(time1 / 1000 + "secondes pour la recherche");
			
			System.out.println("Done");
		}
		
		else {
			System.out.println("Usage :\n\t java -jar readMapper.jar <genome> <reads> <longueur des graines> <match> <sub> <gap> <taux d'erreurs acceptees>");
		}

	}

}
