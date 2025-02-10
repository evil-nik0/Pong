package dev.niko;

import java.awt.event.*;

public class Controles implements KeyListener {

	private boolean pressingSpace;
	private boolean pressingZ;
	
	public Controles() {
		pressingSpace = false;
		pressingZ = false;
	}
	
	public boolean isZPressed() {
		return pressingZ;
	}
	
	public boolean isSpacePressed() {
		return pressingSpace;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				pressingSpace = true;
				break;
			case KeyEvent.VK_Z:
				pressingZ = true;
				break;
			default:
				
				break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				if(pressingSpace) {
					pressingSpace = false;
				}
				break;
			case KeyEvent.VK_Z:
				pressingZ = false;
				break;
			default:
				
				break;
		}
	}
	public void keyTyped(KeyEvent e) {
		return;
	}
	
}
