package dev.niko;

import java.awt.event.*;

public class Raqueta implements KeyListener {
	public double alturaCentro;
	public int player;
	public boolean P1UP, P1DOWN, P2UP, P2DOWN;
	
	public static int LONGITUD = 103;
	public static int ANCHO = 23;
	public static double VELOCIDAD = 10;
	public static int X_POS_P1 = ANCHO;
	public static int X_POS_P2 = Cancha.width - ANCHO;
	
	static {
		if(LONGITUD % 2 == 0 || ANCHO % 2 == 0) throw new RuntimeException("Las raquetas tienen que tener longitud y ancho impares !!");
	}
	
	
	public Raqueta(int player) {
		if(player != 1 && player != 2) throw new RuntimeException("El argumento del constructor de Raqueta tiene que ser 1 o 2!");
		this.player = player;
		alturaCentro = Cancha.height / 2;
		P1UP = P1DOWN = P2UP = P2DOWN = false;
	}
	public void update() {
		if(player == 1 && P1UP) alturaCentro -= VELOCIDAD;
		else if(player == 1 && P1DOWN) alturaCentro += VELOCIDAD;
		else if(player == 2 && P2UP) alturaCentro -= VELOCIDAD;
		else if(player == 2 && P2DOWN) alturaCentro += VELOCIDAD;
		
		if(alturaCentro - (LONGITUD-1)/2 < 0) alturaCentro = (LONGITUD+1)/2;
		else if(alturaCentro + (LONGITUD-1)/2 > Cancha.height) alturaCentro = Cancha.height - (LONGITUD-1)/2;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				if(player == 1) P1UP = true;
				break;
			case KeyEvent.VK_S:
				if(player == 1) P1DOWN = true;
				break;
			case KeyEvent.VK_UP:
				if(player == 2) P2UP = true;
				break;
			case KeyEvent.VK_DOWN:
				if(player == 2) P2DOWN = true;
				break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				if(player == 1) P1UP = false;
				break;
			case KeyEvent.VK_S:
				if(player == 1) P1DOWN = false;
				break;
			case KeyEvent.VK_UP:
				if(player == 2) P2UP = false;
				break;
			case KeyEvent.VK_DOWN:
				if(player == 2) P2DOWN = false;
				break;
		}
	}
	public void keyTyped(KeyEvent e) {
		return;
	}
}

