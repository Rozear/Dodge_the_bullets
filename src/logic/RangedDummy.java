package logic;

import graphics.DrawingUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.IRenderableHolder;

public class RangedDummy extends RangedEnemy {
	
	
	public RangedDummy(float x, float y) {
		super(x, y, Math.PI, 10, 20);
		// TODO Auto-generated constructor stub
		this.hp = 1;
		this.givenExp = 3;
		this.bulletSpawner = null;
		setNewPoint();
//		new NormalBulletSpawner(this).start();
	}
	
	@Override
	void update() {
		
		if(!this.isDestroy() && walkTo(pointX, pointY, 10)){
			if(this.bulletSpawner == null){
				this.spawnBullet(new BurstBulletSpawner(this, 1, 12, 1000));
			}
			move();
			this.focusOnPlayer();
			if(!this.bulletSpawner.isAlive()){
				this.spawnBullet(new BurstBulletSpawner(this, 1, 12, 1000));
			}
		}
	}
	
	@Override
	public synchronized void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtil.drawAvatarBox(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar1);
		DrawingUtil.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar1);
//		DrawingUtil.drawHitBox(gc, this.getX(), this.getY(), this.getRadius(), Color.BLACK);
		DrawingUtil.drawHP(gc, this);
	}
	
}
