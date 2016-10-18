package alignement;

import java.util.List;

import utils.MyFileReader;

public class Main {

	public static void main(String[] args) {
		String genome = MyFileReader.loadGenome("data/ebolavirus.fa");
		genome += "$";
		List<Read> reads = MyFileReader.loadReads("data/ebolareads33.fa");
		
		Mapper map = new Mapper(genome, reads);
		ScoresSet ss = new ScoresSet(5, -4, -10);
		long deb = System.currentTimeMillis();
		System.out.println("Running...");
		map.run(25, ss, 0.1);
		long time = System.currentTimeMillis() - deb;
		System.out.println(time / 1000 + "secondes");
		

	}

}
