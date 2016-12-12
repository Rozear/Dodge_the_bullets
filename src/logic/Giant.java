package logic;

import graphics.DrawingUtility;
import javafx.scene.canvas.GraphicsContext;
import main.IRenderableHolder;

public class Giant extends Enemy {

	public Giant(float x, float y) {
		super(x, y, Math.PI, 1, 50);
		// TODO Auto-generated constructor stub
		this.setHp(12);
		this.givenExp = 60;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtility.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.trollModel);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		move();
		this.focusOnPlayer();
	}

}
