package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Enemy;
import logic.Player;
import utilities.Configuration;

public class DrawingUtility {
	
	public static void drawRotateAvatar(GraphicsContext gc, float x, float y, double angle, Image image){
		gc.translate(x, y);
		gc.rotate(Math.toDegrees(angle) + 90);
		gc.drawImage(image, -image.getHeight()/2, -image.getHeight()/2, image.getHeight(), image.getHeight());
		gc.rotate(-Math.toDegrees(angle) - 90);
		gc.translate(-x, -y);
	}
	
	public static void drawAura(GraphicsContext gc, float x, float y, float radius){

		gc.setGlobalAlpha(0.5);
		gc.setFill(Color.RED);
		gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		gc.setGlobalAlpha(1);

	}
	
	public static void drawAvatarBox(GraphicsContext gc, float x, float y, double angle, Image image){
		gc.translate(x, y);
		gc.setStroke(Color.RED);
		gc.rotate(Math.toDegrees(angle));
		gc.strokeRect(-20, -20, 40, 40);
		gc.rotate(-Math.toDegrees(angle));
		gc.translate(-x, -y);
	}
	
	public static void drawHitBox(GraphicsContext gc, float x, float y, int radius, Color color){
		gc.setFill(color);
		gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	}
	public static void drawHP(GraphicsContext gc, Enemy e){
		gc.setFill(Color.BLACK);
		gc.fillText("HP : " + e.getHp(), e.getX() - e.getRadius(), e.getY() - 30);
	}
	
	public static void drawHP(GraphicsContext gc, Player e){
		gc.setFill(Color.BLACK);
		gc.fillText("HP : " + e.getHp(), e.getX() - e.getRadius(), e.getY() - 30);
	}
	
	public static void drawBG(GraphicsContext gc){
		for(int y = 0; y < Configuration.ARENA_HEIGHT + IRenderableHolder.brickFloor.getHeight(); y += IRenderableHolder.brickFloor.getHeight()){
			for(int x = 0; x < Configuration.ARENA_WIDTH + IRenderableHolder.brickFloor.getWidth(); x += IRenderableHolder.brickFloor.getWidth()){
				gc.drawImage(IRenderableHolder.brickFloor, x, y);
			}
		}
	}
	
}
