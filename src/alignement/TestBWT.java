package alignement;

public class TestBWT {

	public static void main(String[] args) {
//		String genome = "que_chaque_chasseur_sachant_chasser_sache_chasser_sans_son_chien$";
		String genome = "CGAGACGAA$";
		
		BWT bwt = new BWT(genome);
		System.out.println(genome);
		
		bwt.suffixArray();
		bwt.transform();
		bwt.rank();
		bwt.firstColumn();
		
		bwt.printTable(bwt.suffixArray);
		bwt.printTable(bwt.L);
		bwt.printTable(bwt.F);
		bwt.printTable(bwt.encode());
		System.out.println(bwt.map);
		System.out.println(bwt.first);
		bwt.printTable(bwt.ranks);
		
		System.out.println(bwt.search("GA"));
		

	}

}
