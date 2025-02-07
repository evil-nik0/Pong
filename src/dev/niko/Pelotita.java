package dev.niko;

public class Pelotita {
	public Vector posicion;
	public Vector velocidad;
	public static int diametro = 21;
	
	public static Vector VELOCIDAD_INICIAL = new Vector(5, 5);
	
	static {
		if(diametro % 2 == 0) throw new RuntimeException("El diametro de la pelotita tiene que ser un numero impar!!");
	}
	
	public Pelotita() {
		reiniciar();
	}
	
	public void reiniciar() {
		this.posicion = new Vector(Cancha.width/2, Cancha.height/2);
		this.velocidad = VELOCIDAD_INICIAL;
	}
	
	public void update() {
		this.posicion = Vector.suma(posicion, velocidad);
	}
}
