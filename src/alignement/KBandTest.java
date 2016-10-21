package alignement;

public class KBandTest {

	public static void main(String[] args) {
		
		String genome = "tgggatggatcaaccctaacagtggtggcacaaactatgcacagaagtttcagggcagggtcaccatgaccagggacacgtccatcagcacagcctacatggagctgagcaggctgagatctgacgacacggccgtgtattactgtgcgagaga";
		Read read = new Read("ttgcacgcattgattgggatgatgataaatactacagcacatctctgaagaccaggctcaccatctccaaggacacctccaaaaaccaggtggtccttacaatgaccaacatggaccctgtggacacggccgtgtattactg");
//		String genome = "attatccgatt";
//		String read = "actatc";
		KBand kb = new KBand(genome, read, 5, -4, -10, 20);
		kb.buildSimMatrix();
		kb.printSimMatrix();
//		kb.printTraceMatrix();
		Alignement align = kb.backtrace();
		System.out.println(align.toString());	
		System.out.println(kb.nbMatches + " matches");
		System.out.println(kb.nbGAPS + " gaps");
	}

}
