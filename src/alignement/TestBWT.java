package alignement;

public class TestBWT {

	public static void main(String[] args) {
		BWT bwt = new BWT("cgagacgaa$");
		bwt.printTable(bwt.suffixArray());
		System.out.println(bwt.run());

	}

}
