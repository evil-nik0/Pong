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
		double[] colisionp1, colisionp2;
		
		pres.addKeyListener(p1);
		pres.addKeyListener(p2);
		pres.addKeyListener(ctrles);
		
		
		//se requiere un latido del modelo cada 1/FPS segundos
		iTime = System.currentTimeMillis();
		while(true) if( System.currentTimeMillis() - iTime > mSPF ) {
			iTime = System.currentTimeMillis();
			
			p1.update();
			p2.update();
			p.update();
			
			/*
			 * chequeo de colisiones
			 */
			//choque con paredes
			if(p.posicion.y - (p.diametro)/2 < 0 || p.posicion.y + (p.diametro)/2 > Cancha.height) {
				p.velocidad.y *= -1;
				p.update();
			}
			//choque con raquetas
			colisionp1 = PhysicsEngine.areRectanglesColliding(p, p1);
			colisionp2 = PhysicsEngine.areRectanglesColliding(p, p2);
			if(colisionp1[1] != -1) {
				Vector normalConMTV = Vector.crearUnitario(colisionp1[1]);
				normalConMTV.multiplicar(colisionp1[0]);
				p.x += normalConMTV.x;
				p.y += normalConMTV.y;
				
			} else if(colisionp2[1] != -1) {
				
			}
			//bingo! osea, choque con linea de puntos
			if(p.posicion.x - p.diametro / 2 <= 0) {
				Score.p2Score++;
				Score.ultimoPunto = 2;
				p.reiniciar();
			} else if(p.posicion.x + p.diametro / 2 >= Cancha.width) {
				Score.p1Score++;
				Score.ultimoPunto = 1;
				p.reiniciar();
			}
			
			pres.repaint();
		}

	}
}
