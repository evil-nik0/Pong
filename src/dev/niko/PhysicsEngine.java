public class PhysicsEngine {

	//la devolucion es {MTV en magnitud, angulo de la normal}; en Pong puede ser 0 90° o 45° si es un choque justo justo con la esquina de la raqueta, o -1 si no hay colision
	//sería mucho más claro el algoritmo si ésto devolviera un objeto con campos MTV y Normal cómo vector, pero al programador le dio un ataque de fiaca
	public static double[] areRectanglesColliding(Rectangle r1, Rectangle r2) {
		//d is for deepness
		
		double dx1 = r1.x - (r2.x + r2.width);
		double dx2 = r2.x - (r1.x + r1.width);
		double dy1 = r1.y - (r2.y + r2.height);
		double dy2 = r2.y - (r1.y + r1.height);
		
		//si ambas profundidades de algun eje tienen el mismo signo, entonces no hay colision
		if(dx1 * dx2 > 0 || dy1 * dy2 > 0) return new double[] {0, -1};
		
		dx1 = Math.abs(dx1) < Math.abs(dx2) ? Math.abs(dx1) : Math.abs(dx2); //usamos la misma variable para guardar la profundidad mas chica
		dy1 = Math.abs(dy1) < Math.abs(dy2) ? Math.abs(dy1) : Math.abs(dy2);
		
		if(dx1 < dy1) return new double[] {dx1, 0}; //la profundidad más corta está en el eje de abscisas
		else if(dy1 < dx1) return new double[] {dy1, 90}; //la profundidad más corta está en el eje de ordenadas
		else return new double[] {dx1, 45}; //chocaron justo vértice con vértice, caso RARO pero puede pasar; es aún más raro dado que se usan doubles y no ints
	}
}
