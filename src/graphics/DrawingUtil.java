package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Enemy;
import logic.Player;

public class DrawingUtil {
	
	public static void drawRotateAvatar(GraphicsContext gc, float x, float y, double angle, Image image){
		gc.translate(x, y);
		gc.rotate(Math.toDegrees(angle));
//		gc.drawImage(image, -image.getWidth()/2, -image.getHeight()/2);
		gc.drawImage(image, -25, -25, 50, 50);
		gc.rotate(-Math.toDegrees(angle));
		gc.translate(-x, -y);
	}
	
	public static void drawAvatarBox(GraphicsContext gc, float x, float y, double angle, Image image){
		gc.translate(x, y);
		gc.setStroke(Color.RED);
		gc.rotate(Math.toDegrees(angle));
		gc.strokeRect(-image.getWidth()/2, -image.getHeight()/2, image.getWidth(), image.getHeight());
		gc.rotate(-Math.toDegrees(angle));
		gc.translate(-x, -y);
	}
	
	public static void drawHitBox(GraphicsContext gc, float x, float y, int radius, Color color){
		gc.setFill(color);
		gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	}
//	public static void drawRotateAvatar(GraphicsContext gc, float x, float y, double angle){
//		gc.translate(x, y);
//		gc.setFill(Color.BLACK);
//		gc.rotate(angle);
//		gc.fillRect(-50,-50,100,100);
//		gc.rotate(-angle);
//		gc.translate(-x, -y);
//	}
	public static void drawHP(GraphicsContext gc, Enemy e){
		gc.setFill(Color.BLACK);
		gc.fillText("HP : " + e.getHp(), e.getX() - e.getRadius(), e.getY() - 30);
	}
	
	public static void drawHP(GraphicsContext gc, Player e){
		gc.setFill(Color.BLACK);
		gc.fillText("HP : " + e.getHp(), e.getX() - e.getRadius(), e.getY() - 30);
	}
	
}
