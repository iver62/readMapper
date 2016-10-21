package alignement;
import java.util.Random;

public class KBand {

	private String genome; 
	private Read read;
	private int match, sub, gap; // les scores
	private int k; // nombre d'erreurs tolerees
	private int[][] sim; // la mmatrice de similarite
	private char[][][] trace; //
	private int n, m; // les longueurs des sequences
	public int nbGAPS, nbMatches;
	
	public KBand(String genome, Read read, int match, int sub, int gap, int k) {
		this.genome = genome;
		this.read = read;
		this.match = match;
		this.sub = sub;
		this.gap = gap;
		this.k = k;
		nbGAPS = 0; nbMatches = 0;
		n = read.length(); m = genome.length();
		sim = new int[n+1][m+1];
		trace = new char[n+1][m+1][3];
	}

	/**
	 * Construit la matrice de similarite entre le bout de genome et le read
	 */
	public void buildSimMatrix() {
		
		// initialisation de la matrice (remplissag de la 1ere colonne et de la 1ere ligne
		sim[0][0] = 0;
		for (int j = 1; j <= k && j <= m; j++) {
			sim[0][j] = sim[0][j-1] + gap; // on affecte toutes les cases de la 1ere ligne a : valeur case gauche + gap
			trace[0][j][2] = 'l'; // on arrive de la gauche
		}
		for (int i = 1; i <= k && i <= n; i++) {
			sim[i][0] = sim[i-1][0] + gap; // on affecte toutes les cases de la 1ere ligne a : valeur case dessus + gap
			trace[i][0][1] = 't';  // on arrive du dessus
		}
		
		// remplissage de l'interieur de la matrice
		for (int i = 1; i <= n; i++) { // on remplit ligne par ligne
			for (int j = 1; j <= m; j++) {
				if (Math.abs(i-j) <= k) {
					int d = (genome.charAt(j-1) == read.charAt(i-1)) ? (sim[i-1][j-1] + match) : (sim[i-1][j-1] + sub); // la valeur de la case en diagonale + match si les caracteres sont egaux, + sub sinon
					int t = (Math.abs(i-1-j) <= k) ? sim[i-1][j] + gap : Integer.MIN_VALUE; // la valeur de la case du dessus + gap
					int l = (Math.abs(i-j+1) <= k) ? sim[i][j-1] + gap : Integer.MIN_VALUE; // la valeur de la case de gauche + gap
					int max = Math.max(d, Math.max(t, l)); // le max des trois valeurs
					if (d == max) { // si d est un max
						trace[i][j][0] = 'd'; // on vient de la diagonale
					}
					if (t == max) { // si t est un max
						trace[i][j][1] = 't'; // on vient du dessus
					}
					if (l == max) { // si t est un max
						trace[i][j][2] = 'l'; // on vient de la gauche
					}
					sim[i][j] = max; // on affecte la valeur maximale a la case courante
				}
			}	
		}
		
	}
	
	/**
	 * Aligne les 2 sequences selon la methode du backtracing
	 */
	public Alignement backtrace() {
		String resU = new String(); // u avec les eventuels gaps
		String resV = new String(); // v avec les eventuels gaps
		String mil = new String(); // l'alignement entre u et v
//		int i = trace.length-1, j = trace[0].length-1; // on parcourt la matrice depuis le coin inferieur droit jusqu'au coin superieur gauche
		int i = n, j = Math.min(m, n+k);
		int score = sim[i][j];
		char[] res; // tableau contenant les differentes possibilites de remonter
		
		while (i != 0 || j != 0) { // on remonte jusqu'a arriver a la case en haut a gauche
			res = new char[3]; // 3 differentes possibilites de remonter
			int l = 0; // le nombre de possibilites de remonter
			if (trace[i][j][0] == 'd') {
				res[l++] = 'd';
			}
			if (trace[i][j][1] == 't') {
				res[l++] = 't';
			}
			if (trace[i][j][2] == 'l') {
				res[l++] = 'l';
			}
			char c = res[new Random().nextInt(l)]; // on en choisit 1 au hasard
			
			switch (c) {
			case 'd': // soit un match, soit une substitution
				resU += genome.charAt(j-1); 
				resV += read.charAt(i-1);
				if (genome.charAt(j-1) == read.charAt(i-1)) { // si les 2 nucleotides sont egaux 
					mil += "|"; // on ajoute une barre
					nbMatches++;
				}
				else { // sinon on ne met rien
					mil += " ";
				}
				i--; j--; // on remonte en diagonale dans la matrice
				break;
			case 'l': // une deletion
				resU += genome.charAt(j-1);
				resV += "-";
				mil += " ";
				j--; // on va dans la case de gauche
				nbGAPS++;
				break;
			case 't': // une insertion
				resU += "-";
				resV += read.charAt(i-1);
				mil += " ";
				i--; // on remonte dans la case du haut
				nbGAPS++;
				break;
			default:
				break;
			}
		}
		
		double prop = (double)nbGAPS/(double)read.length() * 100;
		return new Alignement(reverse(resU), reverse(resV), reverse(mil), score, prop, read.getName());
	}
	
	private String reverse(String str) {
		String rev = new String();
		for (int i = str.length()-1; i >= 0; i--) {
			rev += str.charAt(i);
		}
		return rev;
	}
	
	public void printSimMatrix() {
		for (int i = 0; i < sim.length; i++) {
			for (int j = 0; j < sim[0].length; j++) {
				System.out.print(sim[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void printTraceMatrix() {
		for (int i = 0; i < trace.length; i++) {
			for (int j = 0; j < trace[0].length; j++) {
				for (int k = 0; k < 3; k++) {
					System.out.print(trace[i][j][k]);
				}
				System.out.print("   ");
			}
			System.out.println();
		}
	}
	
}
