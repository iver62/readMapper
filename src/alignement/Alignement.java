package alignement;

public class Alignement {
	
	private String boutGenome;
	private String read;
	private String matches;
	private int score;
	private double prop;
	
	public Alignement(String boutGenome, String read, String matches, int score, double prop) {
		this.boutGenome = boutGenome;
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


	@Override
	public String toString() {
		return boutGenome + "\n" + matches + "\n" + read + "\n" + score + "\n" + prop;
	}

}
