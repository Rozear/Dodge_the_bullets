package graphics;

import gui.Theme;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Enemy;
import logic.Player;
import utilities.Configuration;

public class DrawingUtility {

	public static void drawRotateAvatar(GraphicsContext gc, float x, float y, double angle, Image image) {
		gc.translate(x, y);
		gc.rotate(Math.toDegrees(angle) + 90);
		gc.drawImage(image, -image.getHeight() / 2, -image.getHeight() / 2, image.getHeight(), image.getHeight());
		gc.rotate(-Math.toDegrees(angle) - 90);
		gc.translate(-x, -y);
	}

	public static void drawPlayerAura(GraphicsContext gc, float x, float y, double angle, Image image) {
		gc.translate(x, y);
		gc.rotate(Math.toDegrees(angle) + 90);
		gc.setGlobalAlpha(0.5);
		gc.drawImage(image, -image.getHeight() / 2, -image.getHeight() / 2, image.getHeight(), image.getHeight());
		gc.setGlobalAlpha(1);
		gc.rotate(-Math.toDegrees(angle) - 90);
		gc.translate(-x, -y);
	}

	public static void drawBG(GraphicsContext gc) {
		for (int y = 0; y < Configuration.ARENA_HEIGHT
				+ IRenderableHolder.brickFloor.getHeight(); y += IRenderableHolder.brickFloor.getHeight()) {
			for (int x = 0; x < Configuration.ARENA_WIDTH
					+ IRenderableHolder.brickFloor.getWidth(); x += IRenderableHolder.brickFloor.getWidth()) {
				if (Theme.chooser == 0)
					gc.drawImage(IRenderableHolder.grassField, x, y);
				else if (Theme.chooser == 1)
					gc.drawImage(IRenderableHolder.desert, x, y);
				else
					gc.drawImage(IRenderableHolder.brickFloor, x, y);
			}
		}
	}

}
