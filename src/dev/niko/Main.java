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
		double multiplicadorPlaying = 1.05, multiplicadorTension = 1.2;
		double multiplicador = multiplicadorPlaying;
		GameStateEnum gs = GameStateEnum.PLAYING;
		
		pres.addKeyListener(p1);
		pres.addKeyListener(p2);
		pres.addKeyListener(ctrles);
		
		Sounds.initSong();
		
		//se requiere un latido del modelo cada 1/FPS segundos. En el caso de que un latido ocupe un intervalo temporal mayor al aquél cociente, el siguiente latido
		//comienza inmediatamente terminado el anterior
		iTime = System.currentTimeMillis();
		while(true) if( System.currentTimeMillis() - iTime > mSPF ) {
		
			iTime = System.currentTimeMillis();
			
			if(gs == GameStateEnum.YOUAREWINNER) {
				if( pres.gs == GameStateEnum.PLAYING ) {
					pres.gs = GameStateEnum.YOUAREWINNER;
					pres.whoIsWinner = Score.ultimoPunto;
				}
				if(ctrles.isSpacePressed()) {
					Score.p1Score = 0;
					Score.p2Score = 0;
					Sounds.initSong();
					p.reiniciar();
					gs = GameStateEnum.PLAYING;
					multiplicador = multiplicadorPlaying;
					pres.gs = GameStateEnum.PLAYING;
					p1.alturaCentro = Cancha.height / 2;
					p2.alturaCentro = Cancha.height / 2;
				}
				pres.repaint();
				continue;
			}
			
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
				Sounds.playSFX("hitw");
			}
			//choque con raquetas
			colisionp1 = PhysicsEngine.areRectanglesColliding(p.r, p1.r);
			colisionp2 = PhysicsEngine.areRectanglesColliding(p.r, p2.r);
			if(colisionp1[1] != -1) {
				Vector normal = Vector.crearUnitario(colisionp1[1]), normalConMTV;
				double porcentajeDeAlejamientoDelCentro; 
				if(colisionp1[1] == 90 && p.posicion.y > p1.alturaCentro) //la pelotita chocó con la cara inferior de la raqueta
					normal = Vector.multiplicacionEscalar(normal, -1);
				else if(colisionp1[1] == 45 && p.posicion.y > p1.alturaCentro) //la pelotita chocó con la esquina inferior izquierda de la raqueta
					normal.y *= -1;
				normalConMTV = Vector.multiplicacionEscalar( normal, colisionp1[0] );
				
				p.posicion = Vector.suma(p.posicion, normalConMTV); //ahora la pelotita no está dentro de la raqueta
				p.velocidad = Vector.reflejarConRespectoANormal( p.velocidad, normal ); //ahora se invirtió la velocidad (mirar guitarreada 1)
				
				porcentajeDeAlejamientoDelCentro = (p.posicion.y-p1.alturaCentro) / ( p1.LONGITUD/2 + p.diametro/2 );
				p.velocidad.y = p.VELOCIDAD_MODULO * porcentajeDeAlejamientoDelCentro;
				p.velocidad.x /= p.velocidad.x;
				p.velocidad.x *= Math.sqrt(p.VELOCIDAD_MODULO * p.VELOCIDAD_MODULO - p.velocidad.y * p.velocidad.y);
				
				p.VELOCIDAD_MODULO *= multiplicador;
				Sounds.playSFX("hitr");
			} else if(colisionp2[1] != -1) {
				Vector normal = Vector.crearUnitario(colisionp2[1]), normalConMTV;
				double porcentajeDeAlejamientoDelCentro; 
				if(colisionp2[1] == 0) normal = Vector.multiplicacionEscalar(normal, -1); //el angulo en realidad sera 180 porque chocó con la cara izquierda
				else if(colisionp2[1] == 90 && p.posicion.y < p2.alturaCentro) //la pelotita chocó con la cara inferior de la raqueta
					normal = Vector.multiplicacionEscalar(normal, -1);
				else if(colisionp2[1] == 45) //la pelotita chocó con una esquina izquierda, inferior o superior. Los ángulos de la normal serán 225 o 135 respect
					if(p.posicion.y > p2.alturaCentro) {
						normal = Vector.crearUnitario(225);
					}
					else {
						normal = Vector.crearUnitario(135);
					}
				normalConMTV = Vector.multiplicacionEscalar( normal, colisionp2[0] );
				
				p.posicion = Vector.suma(p.posicion, normalConMTV); //ahora la pelotita no está dentro de la raqueta
				p.velocidad = Vector.reflejarConRespectoANormal( p.velocidad, normal ); //ahora se invirtió la velocidad (mirar guitarreada 1)
				
				porcentajeDeAlejamientoDelCentro = (p.posicion.y-p2.alturaCentro) / ( p2.LONGITUD/2 + p.diametro/2 );
				p.velocidad.y = p.VELOCIDAD_MODULO * porcentajeDeAlejamientoDelCentro;
				p.velocidad.x /= (-p.velocidad.x);
				p.velocidad.x *= Math.sqrt(p.VELOCIDAD_MODULO * p.VELOCIDAD_MODULO - p.velocidad.y * p.velocidad.y);
				
				p.VELOCIDAD_MODULO *= multiplicador;
				
				Sounds.playSFX("hitr");
			}
			//bingo! osea, choque con linea de puntos
			if(p.posicion.x - p.diametro / 2 <= 0) {
				Score.p2Score++;
				Score.ultimoPunto = 2;
				p.reiniciar();
				Sounds.playSFX("point");
				if(Score.p2Score >= Score.MAXSCORE - Score.howMuchForTension && Sounds.songPlaying) {
					Sounds.initTension();
					multiplicador = multiplicadorTension;
				}
				if(Score.p2Score == Score.MAXSCORE) {
					Sounds.initVictory();
					gs = GameStateEnum.YOUAREWINNER;
				}
					
			} else if(p.posicion.x + p.diametro / 2 >= Cancha.width) {
				Score.p1Score++;
				Score.ultimoPunto = 1;
				p.reiniciar();
				Sounds.playSFX("point");
				if(Score.p1Score >= Score.MAXSCORE - Score.howMuchForTension && Sounds.songPlaying) {
					Sounds.initTension();
					multiplicador = multiplicadorTension;
				}
				if(Score.p1Score == Score.MAXSCORE) {
					Sounds.initVictory();
					gs = GameStateEnum.YOUAREWINNER;
				}
			}
			
			pres.repaint();
			
			if(ctrles.isZPressed()) mSPF = Math.floorDiv(1000, 10);
			else mSPF = Math.floorDiv(1000, FPS);
			
			if(Double.isNaN(p.posicion.x) || Double.isNaN(p.posicion.y)) p.reiniciar(); //ésto a veces pasa, cuándo choca la pelotita con alguna cara vertical y ésta
			//se está moviendo
		}

	}
}



/*
 * NOTA 1: cabe destacar que éste algoritmo tan sui generis para la respuesta de colisión, que no está basado en el modelo de la transferencia de momento de la física cinemática,
 * tiene un error en el contexto actual, y es que al producirse una colisión del proyectil con una línea horizontal del recipiente, la inversión del vector con respecto a la 
 * normal se produce incorrectamente, porque el susodicho vector invierte su dirección múltiples veces, SÓLO en el caso de que el recipiente se encuentre en movimiento.
 * Entonces, en pocas palabras, anda pal poto.
 * PEEERO la solución para ésto es sencilla: hacer la raqueta tan finita que la probabilidad de un choque con respuesta incorrecto sea lo más cercano posible a cero, sin 
 * comprometer la integridad de la experiencia del usuario.
 * Así que, MISSION ... COMPRIT !!!
*/
