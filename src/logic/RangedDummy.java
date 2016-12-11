package logic;

import graphics.DrawingUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.IRenderableHolder;

public class RangedDummy extends RangedEnemy {
	
	public RangedDummy(float x, float y) {
		super(x, y, Math.PI, 10, 20);
		// TODO Auto-generated constructor stub
		this.hp = 3;
		this.givenExp = 30;
		this.bulletPattern = new NormalPattern(this, 3, 6000, BulletPattern.DEFAULT_BURST_DELAY);
		this.bulletSpawner = new BulletSpawner(this.bulletPattern);
		setNewPoint();
//		new NormalBulletSpawner(this).start();
	}
	
	@Override
	void update() {
		
		if(!this.isDestroy() && walkTo(pointX, pointY)){
				this.focusOnPlayer();
				this.spawnBullet();
				if(!this.bulletSpawner.isAlive()){
					this.setNewPoint();
					this.bulletSpawner = new BulletSpawner(bulletPattern);
				}
		}

	}
	
	@Override
	public synchronized void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtility.drawAvatarBox(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar1);
		DrawingUtility.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar1);
//		DrawingUtil.drawHitBox(gc, this.getX(), this.getY(), this.getRadius(), Color.BLACK);
		DrawingUtility.drawHP(gc, this);
	}
	
}
