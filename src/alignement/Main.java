package alignement;

import java.util.List;

import utils.MyFileReader;

public class Main {

	public static void main(String[] args) {
		String genome = MyFileReader.loadGenome("data/ebolavirus.fa");
		genome += "$";
		List<Read> reads = MyFileReader.loadReads("data/ebolareads33.fa");
		
//		System.out.println(genome);
//		System.out.println(reads.toString());
		
		Mapper map = new Mapper(genome, reads);
		long deb = System.currentTimeMillis();
		map.run(25, 5, 4, -10, 0.1);
		long time = System.currentTimeMillis() - deb;
		System.out.println(time / 1000 + "secondes");
		

	}

}
