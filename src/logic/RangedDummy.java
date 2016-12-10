package logic;

import graphics.DrawingUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.IRenderableHolder;

public class RangedDummy extends RangedEnemy {
	
	public RangedDummy(float x, float y) {
		super(x, y, Math.PI, 0, 10);
		// TODO Auto-generated constructor stub
		this.givenExp = 3;
		this.spawnBullet();
//		new NormalBulletSpawner(this).start();
	}
	
	@Override
	void update() {
		if(!this.isDestroy()){
			move();
			this.focusOnPlayer();
			if(!this.bulletSpawner.isAlive()){
				this.spawnBullet();
			}
		}
	}
	
	@Override
	public synchronized void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtil.drawAvatarBox(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar);
		DrawingUtil.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar);
		DrawingUtil.drawHitBox(gc, this.getX(), this.getY(), this.getRadius(), Color.BLACK);
		DrawingUtil.drawHP(gc, this);
	}

	public void spawnBullet() {
		// TODO Auto-generated method stub
		this.bulletSpawner = new BurstBulletSpawner(this, 1, 12, 1000);
		this.bulletSpawner.start();
	}
}
