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
		
		
		//se requiere un latido del modelo cada 1/FPS segundos. En el caso de que un latido ocupe un intervalo temporal mayor al aquél cociente, el siguiente latido
		//comienza inmediatamente terminado el anterior
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
			colisionp1 = PhysicsEngine.areRectanglesColliding(p.r, p1.r);
			colisionp2 = PhysicsEngine.areRectanglesColliding(p.r, p2.r);
			if(colisionp1[1] != -1) {
				Vector normal = Vector.crearUnitario(colisionp1[1]), normalConMTV;
				if(colisionp1[1] == 90 && p.posicion.y > p1.alturaCentro) //la pelotita chocó con la cara inferior de la raqueta
					normal = Vector.multiplicacionEscalar(normal, -1);
				else if(colisionp1[1] == 45 && p.posicion.y > p1.alturaCentro) //la pelotita chocó con la esquina inferior izquierda de la raqueta
					normal.y *= -1;
				normalConMTV = Vector.multiplicacionEscalar( normal, colisionp1[0] );
				
				p.posicion = Vector.suma(p.posicion, normalConMTV); //ahora la pelotita no está dentro de la raqueta
				p.velocidad = Vector.reflejarConRespectoANormal( p.velocidad, normal ); //ahora se invirtió la velocidad con la ley de Schnell
				
				
				
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
