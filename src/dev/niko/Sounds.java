package dev.niko;

import javax.sound.sampled.*;

public class Sounds {
	public static Clip song, tension, victory, hitr, hitw, point;
	public static boolean songPlaying = false, tensionPlaying = false, victoryPlaying = false;
	
	static {
		Cancha cancha = new Cancha(); //solo es para obtener un class loader
		try {
			song = AudioSystem.getClip();
			tension = AudioSystem.getClip();
			victory = AudioSystem.getClip();
			hitr = AudioSystem.getClip();
			hitw = AudioSystem.getClip();
			point = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("hitr.wav") );
			hitr.open(ais);
			ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("hitw.wav") );
			hitw.open(ais);
			ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("point.wav") );
			point.open(ais);
			ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("song.wav") );
			song.open(ais);
			ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("tension.wav") );
			tension.open(ais);
			ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("fanfare.wav") );
			victory.open(ais);
		} catch(Throwable t) {
			System.err.println("Erroraso al crear los sonidos!!: " + t.toString());
			//throw new RuntimeException("Erroraso al crear los sonidos!!");
		}
	}
	
	//dominio de sfx: hitr, hitw, point
	public static void playSFX(String sfx) {
		if( sfx.equals("hitr") ) {
			hitr.stop();
			hitr.setFramePosition(0);
			hitr.start();
		} else if( sfx.equals("hitw") ) {
			hitw.stop();
			hitw.setFramePosition(0);
			hitw.start();
		} else if( sfx.equals("point") ) {
			point.stop();
			point.setFramePosition(0);
			point.start();
		} else {
		
		}
	}
	
	public static void initSong() {
		if( songPlaying ) return;
		tension.stop();
		victory.stop();
		
		song.stop();
		song.setFramePosition(0);
		song.start();
		
		songPlaying = true;
		tensionPlaying = false;
		victoryPlaying = false;
	}
	
	public static void initTension() {
		if( tensionPlaying ) return;
		song.stop();
		victory.stop();
		
		tension.stop();
		tension.setFramePosition(0);
		tension.start();
		
		songPlaying = false;
		tensionPlaying = true;
		victoryPlaying = false;
	}
	
	public static void initVictory() {
		if( victoryPlaying ) return;
		tension.stop();
		song.stop();
		
		victory.stop();
		victory.setFramePosition(0);
		victory.start();
		
		songPlaying = false;
		tensionPlaying = false;
		victoryPlaying = true;
	}
	
}
