package logic;

import graphics.DrawingUtil;
import javafx.scene.canvas.GraphicsContext;
import main.IRenderableHolder;

public class MeleeDummy extends MeleeEnemy {
	
	public MeleeDummy(float x, float y) {
		super(x, y, Math.PI, 6, 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtil.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar2);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		move();
		this.focusOnPlayer();
	}

}
