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
				
			
			pres.repaint();
		}

	}
	
}
