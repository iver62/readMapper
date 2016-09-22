package alignement;

public class Test {

	public static void main(String[] args) {
		
		String genome = "tgggatggatcaaccctaacagtggtggcacaaactatgcacagaagtttcagggcagggtcaccatgaccagggacacgtccatcagcacagcctacatggagctgagcaggctgagatctgacgacacggccgtgtattactgtgcgagaga";
		String read = "ttgcacgcattgattgggatgatgataaatactacagcacatctctgaagaccaggctcaccatctccaaggacacctccaaaaaccaggtggtccttacaatgaccaacatggaccctgtggacacggccgtgtattactg";
//		String genome = "attatc";
//		String read = "actatc";
		kBand kea = new kBand(genome, read, 5, -4, -10, 20);
		kea.buildSimMatrix();
//		kea.printSimMatrix();
//		kea.printTraceMatrix();
		String alignement = kea.align();
		System.out.println(alignement);		
		System.out.println(kea.nbMatches + " matches");
		System.out.println(kea.nbGAPS + " gaps");
	}

}
