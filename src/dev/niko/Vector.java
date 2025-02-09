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
	
	//angulo está especificado en degrees, radianes es para nerds <-- eso lo escribí antes de saber que Math.cos y Math.sin usan radianes.
	//por qué nadie me lo dijo debo haber quedado cómo un idiota !!!
	public static Vector crearUnitario(double angulo) {
		if(angulo < 0) System.out.println("Se mandó un ángulo negativo a Vector.crearUnitario(), no estoy seguro si funciona bien con ésto");
		
		double radians = Math.toRadians(angulo);
		return new Vector(Math.cos(radians), Math.sin(radians));
	}
	
	public static void multiplicacionEscalar(Vector v, double escalar) {
		v.x *= escalar;
		v.y *= escalar;
	}
	
	public double modulo() {
		return Math.sqrt(x*x + y*y);
	}
	
	public static Vector productoEscalar(Vector u, Vector v) {
		return u.x * v.x + u.y * v.y;
	}
	
	public static Vector proyeccion(Vector v, Vector d) {
		if(d.modulo() != 1) System.out.println(
		String.format("Ojo! Se llamo a Vector.proyeccion() con un vector de dirección que no tiene módulo 1: v=%s, d=%s", v.toString(), d.toString())
		);
		
		return multiplicacionEscalar( d, productoEscalar(v, d).modulo() );
		
	}
	
	public String toString() {
		return String.format("(%f,%f)", x, y);
	}
}

