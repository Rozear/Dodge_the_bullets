package logic;

import graphics.DrawingUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.IRenderableHolder;

public class Dummy extends Enemy {

	public Dummy(float x, float y) {
		super(x, y, Math.PI, 0, 0);
		// TODO Auto-generated constructor stub
		this.givenExp = 3;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtil.drawAvatarBox(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar);
		DrawingUtil.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar);
		DrawingUtil.drawHitBox(gc, this.getX(), this.getY(), this.getRadius(), Color.BLACK);
		DrawingUtil.drawHP(gc, this);
	}
}
