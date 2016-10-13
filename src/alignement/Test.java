package alignement;

public class Test {

	public static void main(String[] args) {
		
		String genome = "tgggatggatcaaccctaacagtggtggcacaaactatgcacagaagtttcagggcagggtcaccatgaccagggacacgtccatcagcacagcctacatggagctgagcaggctgagatctgacgacacggccgtgtattactgtgcgagaga";
		String read = "ttgcacgcattgattgggatgatgataaatactacagcacatctctgaagaccaggctcaccatctccaaggacacctccaaaaaccaggtggtccttacaatgaccaacatggaccctgtggacacggccgtgtattactg";
//		String genome = "attatccgatt";
//		String read = "actatc";
		kBand kb = new kBand(genome, read, 5, -4, -10, 18);
		kb.buildSimMatrix();
//		kb.printSimMatrix();
//		kea.printTraceMatrix();
		String alignement = kb.align();
		System.out.println(alignement);		
		System.out.println(kb.nbMatches + " matches");
		System.out.println(kb.nbGAPS + " gaps");
	}

}
