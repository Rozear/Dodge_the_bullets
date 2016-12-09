package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DrawingUtil {
	
	public static void drawRotateAvatar(GraphicsContext gc, float x, float y, double angle, Image image){
		gc.translate(x, y);
		gc.setFill(Color.BLACK);
		gc.rotate(angle);
		gc.fillRect(-image.getWidth()/2, -image.getHeight()/2, image.getWidth(), image.getHeight());
		gc.rotate(-angle);
		gc.translate(-x, -y);
	}
	
}
