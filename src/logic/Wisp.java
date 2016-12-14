package logic;

import java.util.Random;

import bulletSpawner.BulletPattern;
import bulletSpawner.BulletSpawner;
import bulletSpawner.SpreadPattern;
import graphics.DrawingUtility;
import graphics.IRenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import utilities.NoBulletSpawnerException;

public class Wisp extends RangedEnemy {

	int delay;
	
	public Wisp(float x, float y) {
		super(x, y, Math.PI, 3, 25);
		// TODO Auto-generated constructor stub
		this.setHp(3);
		this.givenExp = 50;
		this.bulletPattern = new SpreadPattern(this, 12, 360, 1, 4000, BulletPattern.DEFAULT_BURST_DELAY);
		this.bulletSpawner = new BulletSpawner(this.bulletPattern);
		setNewPoint();
		this.delay = (int) (( new Random().nextFloat() * 300 ) + 100);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(Math.abs(this.angle) <= Math.PI / 2 ){
			gc.drawImage(IRenderableHolder.wispModel, x - IRenderableHolder.wispModel.getWidth() / 2, y - IRenderableHolder.wispModel.getHeight() / 2);
		} else {
			gc.drawImage(IRenderableHolder.wispModelLeft, x - IRenderableHolder.wispModel.getWidth() / 2, y - IRenderableHolder.wispModel.getHeight() / 2);
		}
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		if(!this.isDestroy()){
			if(this.walkTo(pointX, pointY)){
				setNewPoint();
			}
			try {
				if(this.delay >=0 )
					delay--;
				else
				this.spawnBullet(this.bulletPattern);
			} catch (NoBulletSpawnerException e) {
				// TODO Auto-generated catch block
			}
		}
	}

}
