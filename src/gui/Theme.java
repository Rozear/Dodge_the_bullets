package gui;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Theme {
    static ClassLoader loader = ClassLoader.getSystemClassLoader();
	static final Media sound2 = new Media(loader.getResource("media/secretbase.mp3").toString());
	static final Media sound1 = new Media(loader.getResource("media/Arabesque.mp3").toString());
	static final Media sound = new Media(loader.getResource("media/magical_theme.mp3").toString());

    static  final Image background = new Image(loader.getResourceAsStream("bg/grass1.png"));
    public  Image bg;
    public  Media music;
    public static Theme theme ;
    public static int chooser;

    public Theme(Image background,Media sound){
    	this.bg = background;
    	this.music = sound;
    }
    	
    static Theme theme1 = new Theme(background,sound);
    static Theme theme2 = new Theme(background,sound1);
    static Theme theme3 = new Theme(background,sound2);
   
    public static void chooseTheme(int i){
    	if(i == 0)
    		theme = theme1;
    	else if(i == 1)
    		theme = theme2;
    	else if(i == 2){
    		theme = theme3;
    	}else
    	    theme = theme1;
    }
    public Media getSound(){
    	return this.music;
    }
    public Image getBackground(){
   		return this.bg;
    }
}
