package logic;

import graphics.DrawingUtility;
import javafx.scene.canvas.GraphicsContext;
import main.IRenderableHolder;

public class Bandit extends Enemy {
	
	public Bandit(float x, float y) {
		super(x, y, Math.PI, 3, 20);
		// TODO Auto-generated constructor stub
		this.hp = 3;
		this.givenExp = 10;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtility.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.banditModel);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		move();
		this.focusOnPlayer();
	}

}
