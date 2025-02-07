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
}
