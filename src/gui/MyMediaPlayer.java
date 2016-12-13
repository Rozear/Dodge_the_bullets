package gui;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyMediaPlayer {
	public static int playing;
	static MediaPlayer mediaPlayer;

	static void musicplay(){

		Theme.chooseTheme(Theme.chooser);
		mediaPlayer = new MediaPlayer(Theme.theme.getSound());
		if(Theme.chooser == 2)
			mediaPlayer.setVolume(0.7);
	    mediaPlayer.play();
	    System.out.println(Theme.chooser);
	}
	
	static void changemusic(){
		playing++;
		Theme.chooser = playing%3;
	}
	public static void stopMusic(){
		mediaPlayer.stop();
	}
}
