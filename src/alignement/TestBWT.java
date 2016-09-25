package alignement;

public class TestBWT {

	public static void main(String[] args) {
		String genome = "que_chaque_chasseur_sachant_chasser_sache_chasser_sans_son_chien$";
		BWT bwt = new BWT(genome);
		System.out.println(genome);
		bwt.printTable(bwt.suffixArray());
		System.out.println(bwt.transform());
		bwt.printTable(bwt.encode());

	}

}
