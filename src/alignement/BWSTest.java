package alignement;

import utils.MyFileReader;

public class BWSTest {

	public static void main(String[] args) {
//		String genome = "que_chaque_chasseur_sachant_chasser_sache_chasser_sans_son_chien$";
//		String genome = "CGAGACGAA$";
		
		String genome = MyFileReader.loadGenome("data/testgenome.txt");
		genome += "$";
				
		BurrowsWheelerStructure bws = new BurrowsWheelerStructure(genome);
		bws.init();
		
//		bwt.printTable(bwt.suffixArray);
//		bwt.printTable(bwt.L);
//		bwt.printTable(bwt.F);
//		bwt.printTable(bwt.encode());
		System.out.println(bws.map);
		System.out.println(bws.first);
//		bwt.printTable(bwt.ranks);
		
		bws.find(new Seed("ggg"));
		

	}

}
