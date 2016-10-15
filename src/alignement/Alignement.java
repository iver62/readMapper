package alignement;

public class Alignement {
	
	private String genomeSequence;
	private String read;
	private String matches;
	private int score;
	private double prop;
	private int position;
	
	public Alignement(String genomeSequence, String read, String matches, int score, double prop) {
		this.genomeSequence = genomeSequence;
		this.read = read;
		this.matches = matches;
		this.score = score;
		this.prop = prop;
	}

	
	public int getScore() {
		return score;
	}

	public double getProp() {
		return prop;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return genomeSequence + "\n" + matches + "\n" + read + "\n" + prop;
	}

}
