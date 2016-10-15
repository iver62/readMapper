package alignement;

import java.util.List;

public class Seed {
	
	private String sequence;
	private Read read;
	private int position; // position dans le read
	private List<Integer> positions; // positions dans le genome
	
	public Seed(String sequence, Read read, int position) {
		this.sequence = sequence;
		this.read = read;
		this.position = position;
	}
	
	public Seed(String sequence) {
		this.sequence = sequence;
	}
	
	public int length() {
		return sequence.length();
	}
	
	public char charAt(int i) {
		return sequence.charAt(i);
	}

	public String getSequence() {
		return sequence;
	}

	public Read getRead() {
		return read;
	}

	public int getPosition() {
		return position;
	}

	public List<Integer> getPositions() {
		return positions;
	}

	public void setPositions(List<Integer> positions) {
		this.positions = positions;
	}

	@Override
	public String toString() {
		return "Seed = " + sequence;
	}

}
