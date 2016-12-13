package gui;

import java.io.File;

import graphics.IRenderableHolder;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Theme {

	public Image bg;
	public Media music;
	public static Theme mainTheme;
	public static int chooser;

	public Theme(Image background, Media sound) {
		this.bg = background;
		this.music = sound;
	}

	private static final Theme THEME_1 = new Theme(IRenderableHolder.grassField, IRenderableHolder.forestBGM);
	private static final Theme THEME_2 = new Theme(IRenderableHolder.desert, IRenderableHolder.desertBGM);
	private static final Theme THEME_3 = new Theme(IRenderableHolder.brickFloor, IRenderableHolder.animeBGM);

	public static void chooseTheme(int i) {
		if (i == 0)
			mainTheme = THEME_1;
		else if (i == 1)
			mainTheme = THEME_2;
		else if (i == 2) {
			mainTheme = THEME_3;
		} else
			mainTheme = THEME_1;
	}

	public Media getSound() {
		return this.music;
	}

	public Image getBackground() {
		return this.bg;
	}
}
