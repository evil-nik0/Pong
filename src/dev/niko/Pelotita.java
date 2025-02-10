package dev.niko;

import java.util.Random;

public class Pelotita {
	public Vector posicion;
	public Vector velocidad;
	public static int diametro = 20;
	public Rectangle r;
	
	private static double VELOCIDAD_MODULO_INICIAL = 10;
	public static double VELOCIDAD_MODULO = VELOCIDAD_MODULO_INICIAL;
	public static Random randomGen;
	
	static {
		if(diametro % 2 != 0) throw new RuntimeException("El diametro de la pelotita tiene que ser un numero par!!");
		randomGen = new Random();
	}
	
	public Pelotita() {
		this.posicion = new Vector(Cancha.width/2, Cancha.height/2);
		randomizeVelocity();
		r = new Rectangle();
		r.x = posicion.x - diametro / 2;
		r.y = posicion.y - diametro / 2;
		r.width = diametro;
		r.height = diametro;
	}
	
	public void reiniciar() {
		this.posicion = new Vector(Cancha.width/2, Cancha.height/2);
		VELOCIDAD_MODULO = VELOCIDAD_MODULO_INICIAL;
		randomizeVelocity();
	}
	
	private void randomizeVelocity() {
		double vy = randomGen.nextInt() % 10;
		double vxRand = Math.sqrt( VELOCIDAD_MODULO * VELOCIDAD_MODULO - vy * vy );
		double vx = randomGen.nextBoolean() ? vxRand : vxRand * (-1);
		velocidad = new Vector(vx, vy);
	}
	
	public void update() {
		this.posicion = Vector.suma(posicion, velocidad);
		r.x = posicion.x - diametro / 2;
		r.y = posicion.y - diametro / 2;
	}
}
