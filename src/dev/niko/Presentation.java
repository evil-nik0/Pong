package dev.niko;

import java.awt.*;
import java.awt.event.*;

public class Presentation extends Frame {
	public Raqueta P1, P2;
	public Pelotita p;
	public GameStateEnum gs;
	public Image doubleBuffer;
	public int whoIsWinner; //puede ser 1 o 2
	
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
		
		Graphics frameG = g;
		g = doubleBuffer.getGraphics();
		
		if(gs == GameStateEnum.PLAYING) {
			int xP1 = P1.X_POS_P1 - (P1.ANCHO)/2;
			int yP1 = Double.valueOf(Math.floor(P1.alturaCentro)).intValue() - (P1.LONGITUD)/2;
			int xP2 = P2.X_POS_P2 - (P2.ANCHO)/2;
			int yP2 = Double.valueOf(Math.floor(P2.alturaCentro)).intValue() - (P2.LONGITUD)/2;
			int xp = Double.valueOf(Math.floor(p.posicion.x)).intValue() - (p.diametro)/2;
			int yp = Double.valueOf(Math.floor(p.posicion.y)).intValue() - (p.diametro)/2;
				
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(Color.WHITE);
			g.fillRect(xP1, yP1, P1.ANCHO, P1.LONGITUD);
			g.fillRect(xP2, yP2, P2.ANCHO, P2.LONGITUD);
			g.fillOval(xp, yp, p.diametro, p.diametro);
			g.drawString(Score.p1Score+"", Cancha.width/2 - 40, 40);
			g.drawString(Score.p2Score+"", Cancha.width/2 + 40, 40);
		} else if(gs == GameStateEnum.YOUAREWINNER) {
		
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(Color.WHITE);
			g.drawString(
				String.format("CONGLATURATION PLAYER %d, YOU ARE A WINNER!!1", whoIsWinner), Cancha.width/2 - 50, Cancha.height/2
			);
			g.drawString(
				String.format("WE GIVE YOU THANKS FOR PLAYING THIS GREAT GAME!"), Cancha.width/2 - 50, Cancha.height/2 + 20
			);
			g.drawString(
				String.format("PRESS SPACE TO PLAY AGAIN!"), Cancha.width/2 - 50, Cancha.height/2 + 40
			);
		}
	
		frameG.drawImage(doubleBuffer, getInsets().left, getInsets().top, null);
		
	}
}
