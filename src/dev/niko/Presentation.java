package dev.niko;

import java.awt.*;
import java.awt.event.*;

public class Presentation extends Frame {
	public Raqueta P1, P2;
	public Pelotita p;
	public GameStateEnum gs;
	public Image doubleBuffer;
	
	public Presentation() {
		super();
	}
	
	public void init(Raqueta P1, Raqueta P2, Pelotita p) {
		this.P1 = P1;
		this.P2 = P2;
		this.p = p;
		gs = GameStateEnum.PLAYING;
		
		setSize(Cancha.width, Cancha.height);
		setTitle("PONG");
		setVisible(true);
		setSize(Cancha.width + getInsets().left + getInsets().right, Cancha.height + getInsets().top + getInsets().bottom);
		doubleBuffer = createImage(Cancha.width, Cancha.height);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	public void paint(Graphics g) {
	
		if(doubleBuffer == null) return;
	
		int xP1 = P1.X_POS_P1 - (P1.ANCHO-1)/2;
		int yP1 = Double.valueOf(Math.floor(P1.alturaCentro)).intValue() - (P1.LONGITUD-1)/2;
		int xP2 = P2.X_POS_P2 - (P2.ANCHO-1)/2;
		int yP2 = Double.valueOf(Math.floor(P2.alturaCentro)).intValue() - (P2.LONGITUD-1)/2;
		int xp = Double.valueOf(Math.floor(p.posicion.x)).intValue() - (p.diametro-1)/2;
		int yp = Double.valueOf(Math.floor(p.posicion.y)).intValue() - (p.diametro-1)/2;
		Graphics frameG = g;
		g = doubleBuffer.getGraphics();
			
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.WHITE);
		g.fillRect(xP1, yP1, P1.ANCHO, P1.LONGITUD);
		g.fillRect(xP2, yP2, P2.ANCHO, P2.LONGITUD);
		g.fillOval(xp, yp, p.diametro, p.diametro);
		g.drawString(Score.p1Score+"", 490, 40);
		g.drawString(Score.p2Score+"", 534, 40);
		
		frameG.drawImage(doubleBuffer, getInsets().left, getInsets().top, null);
		
	}
}
