package dev.niko;

import java.awt.event.*;

public class Controles implements KeyListener {
	private boolean listeningSpace;
	private boolean spacePressed;
	private boolean pressingSpace;
	
	public Controles() {
		listeningSpace = false;
		spacePressed = false;
		pressingSpace = false;
	}
	
	public void listenForSpace() {
		listeningSpace = true;
		spacePressed = false;
	}
	public boolean isSpacePressed() {
		if(!spacePressed) return false;
		else {
			listeningSpace = false;
			return true;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(!listeningSpace) return;
		else switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				pressingSpace = true;
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
					spacePressed = true;
				}
				break;
			default:
				
				break;
		}
	}
	public void keyTyped(KeyEvent e) {
		return;
	}
	
}
