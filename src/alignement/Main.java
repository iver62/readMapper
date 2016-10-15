package alignement;

import java.util.List;

import utils.MyFileReader;

public class Main {

	public static void main(String[] args) {
		String genome = MyFileReader.loadGenome("data/testgenome.txt");
		genome += "$";
		List<Read> reads = MyFileReader.loadReads("data/testreads.txt");
		
		System.out.println(genome);
		System.out.println(reads.toString());
		
		Mapper map = new Mapper(genome, reads);
		map.run(2, 5, 4, -10, 2, 10);

	}

}
