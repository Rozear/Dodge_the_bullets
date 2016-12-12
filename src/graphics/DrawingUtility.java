package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Enemy;
import logic.Entity;
import logic.Player;
import main.IRenderableHolder;
import utilities.Configuration;

public class DrawingUtility {
	
	public static void drawRotateAvatar(GraphicsContext gc, float x, float y, double angle, Image image){
		gc.translate(x, y);
		gc.rotate(Math.toDegrees(angle));
//		gc.drawImage(image, -image.getWidth()/2, -image.getHeight()/2);
		gc.drawImage(image, -20, -20, 40, 40);
		gc.rotate(-Math.toDegrees(angle));
		gc.translate(-x, -y);
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
	
	public static void drawBG(GraphicsContext gc){
//		gc.setFill(Color.DARKGREEN);
//		gc.fillRect(0, 0, Configuration.SCREEN_WIDTH, Configuration.SCREEN_HEIGHT);
//		gc.setGlobalAlpha(0.5);
		for(int y = 0; y < Configuration.ARENA_HEIGHT + IRenderableHolder.bg.getHeight(); y += IRenderableHolder.bg.getHeight()){
			for(int x = 0; x < Configuration.ARENA_WIDTH + IRenderableHolder.bg.getWidth(); x += IRenderableHolder.bg.getWidth()){
				gc.drawImage(IRenderableHolder.bg, x, y);
			}
		}
		gc.setFill(Color.rgb(120, 160, 104));
		gc.fillRect(0, 0, Configuration.ARENA_WIDTH, 5);
		gc.fillRect(0, Configuration.ARENA_HEIGHT - 5, Configuration.ARENA_WIDTH, 5);
		gc.fillRect(0, 0, 5, Configuration.ARENA_HEIGHT);
		gc.fillRect(Configuration.ARENA_WIDTH - 5, 0, 5, Configuration.ARENA_HEIGHT);
//		gc.setGlobalAlpha(1);
	}
	
	public static void flashScreen(GraphicsContext gc){
		
	}
	
	
}
