package gui;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyMediaPlayer {
	static boolean playing;
	static MediaPlayer mediaPlayer;
	static Media sound2 = new Media(new File("secretbase.mp3").toURI().toString());
    static  Media sound = new Media(new File("MainMenu.mp3").toURI().toString());
    
	static void musicplay(){
	    mediaPlayer = new MediaPlayer(sound);
	    mediaPlayer.play();
	    playing = true;
	}
	
	static void changemusic(){
		
		if(playing){
			mediaPlayer.stop();
			mediaPlayer = new MediaPlayer( new Media(new File("secretbase.mp3").toURI().toString()));
			playing = false;
			mediaPlayer.play();
		}
		else {
			mediaPlayer.stop();
			mediaPlayer = new MediaPlayer( new Media(new File("MainMenu.mp3").toURI().toString()));
			playing = true;
			mediaPlayer.play();
		}
	}
}
