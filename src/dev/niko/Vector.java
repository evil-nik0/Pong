package dev.niko;

public class Vector {
	public double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vector suma(Vector u, Vector v) {
		return new Vector(u.x+v.x, u.y+v.y);
	}
	
	//usar ésto sólo para ángulo entre 0 y 90 inclusive!! Also, en degrees.
	public static Vector crearUnitario(double angulo) {
		return new Vector(Math.cos(angulo), Math.sin(angulo));
	}
	
	public void multiplicar(double escalar) {
		this.x *= escalar;
		this.y *= escalar;
	}
}
