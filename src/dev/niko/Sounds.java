package dev.niko;

import javax.sound.sampled.*;

public class Sounds {
	public static Clip song, tension, victory;
	public static boolean songPlaying = false, tensionPlaying = false, victoryPlaying = false;
	
	//dominio de sfx: hitr, hitw, point
	public static void playSFX(String sfx) {
		Cancha cancha = new Cancha(); //solo es para obtener un class loader
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource(sfx + ".wav") );
			clip.open(ais);
			clip.start();
		} catch(Throwable t) {
			System.err.println(t);
		}
	}
	
	public static void initSong() {
		Cancha cancha = new Cancha(); //solo es para obtener un class loader
		if(song != null) song.stop();
		if(tension != null) tension.stop();
		if(victory != null) victory.stop();
		try {
			song = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("song.wav") );
			song.open(ais);
			song.start();
			songPlaying = true;
			tensionPlaying = false;
			victoryPlaying = false;
		} catch(Throwable t) {
			System.err.println(t);
		}
	}
	
	public static void initTension() {
		Cancha cancha = new Cancha(); //solo es para obtener un class loader
		if(song != null) song.stop();
		if(tension != null) tension.stop();
		if(victory != null) victory.stop();
		try {
			tension = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("tension.wav") );
			tension.open(ais);
			tension.start();
			songPlaying = false;
			tensionPlaying = true;
			victoryPlaying = false;
		} catch(Throwable t) {
			System.err.println(t);
		}
	}
	
	public static void initVictory() {
		Cancha cancha = new Cancha(); //solo es para obtener un class loader
		if(song != null) song.stop();
		if(tension != null) tension.stop();
		if(victory != null) victory.stop();
		try {
			victory = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream( cancha.getClass().getResource("fanfare.wav") );
			victory.open(ais);
			victory.start();
			songPlaying = false;
			tensionPlaying = false;
			victoryPlaying = true;
		} catch(Throwable t) {
			System.err.println(t);
		}
	}
	
	public static void stopSong() {
		if(song != null) {
			song.stop();
			songPlaying = false;
		}
	}
	public static void stopTension() {
		if(tension != null) {
			tension.stop();
			tensionPlaying = false;
		}
	}
}
