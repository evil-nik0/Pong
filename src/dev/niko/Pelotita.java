package dev.niko;

public class Pelotita {
	public Vector posicion;
	public Vector velocidad;
	public static int diametro = 20;
	public Rectangle r;
	
	public static Vector VELOCIDAD_INICIAL = new Vector(4, 2);
	
	static {
		if(diametro % 2 != 0) throw new RuntimeException("El diametro de la pelotita tiene que ser un numero par!!");
	}
	
	public Pelotita() {
		this.posicion = new Vector(Cancha.width/2, Cancha.height/2);
		this.velocidad = VELOCIDAD_INICIAL;
		r = new Rectangle();
		r.x = posicion.x - diametro / 2;
		r.y = posicion.y - diametro / 2;
		r.width = diametro;
		r.height = diametro;
	}
	
	public void reiniciar() {
		this.posicion = new Vector(Cancha.width/2, Cancha.height/2);
		this.velocidad = VELOCIDAD_INICIAL;
	}
	
	public void update() {
		this.posicion = Vector.suma(posicion, velocidad);
		r.x = posicion.x - diametro / 2;
		r.y = posicion.y - diametro / 2;
	}
}
