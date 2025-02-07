package dev.niko;

public class Main {

	public static int FPS = 60;

	public static void main(String args[]) {
		Raqueta p1 = new Raqueta(1);
		Raqueta p2 = new Raqueta(2);
		Pelotita p = new Pelotita();
		Controles ctrles = new Controles();
		Presentation pres = new Presentation();
		pres.init(p1, p2, p);
		long mSPF = Math.floorDiv(1000, FPS);
		long iTime;
		
		pres.addKeyListener(p1);
		pres.addKeyListener(p2);
		pres.addKeyListener(ctrles);
		
		
		iTime = System.currentTimeMillis();
		while(true) if( System.currentTimeMillis() - iTime > mSPF ) {
			iTime = System.currentTimeMillis();
			
			p1.update();
			p2.update();
			p.update();
			
			//chequeo de colisiones
			if(p.posicion.y - (p.diametro)/2 < 0 || p.posicion.y + (p.diametro)/2 > Cancha.height) {
				p.velocidad.y *= -1;
				p.update();
			}
			if(areRectanglesColliding(p.r, p1.r) || areRectanglesColliding(p.r, p2.r)) {
				p.velocidad.x *= -1;
				p.update();
			}
			
			pres.repaint();
		}

	}
	
	public static boolean areRectanglesColliding(Rectangle r1, Rectangle r2) {
		if(areIntervalsDisjoint( r1.x, r1.x+r1.width, r2.x, r2.x+r2.width )) return false;
		if(areIntervalsDisjoint( r1.y, r1.y+r1.height, r2.y, r2.y+r2.height )) return false;
		return true;
	}
	
	public static boolean areIntervalsDisjoint(double i1, double f1, double i2, double f2) {
		if(i2-i1 > f1 - i1) return true;
		else if(i2-i1 < 0 && f2-i1 < 0) return true;
		else return false;
	}
	
}
