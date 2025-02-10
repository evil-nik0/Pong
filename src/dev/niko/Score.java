package dev.niko;

public class Score {
	public static int p1Score = 0, p2Score = 0;
	public static int MAXSCORE = 10;
	public static int ultimoPunto = -1; //puede ser 1 o 2
	public static int howMuchForTension = 3;
	
	static {
		if(MAXSCORE < howMuchForTension) throw new RuntimeException("Cheee tan cortito será el juego??");
	}
}
