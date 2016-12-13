package logic;

import bulletSpawner.BulletPattern;
import bulletSpawner.BulletSpawner;
import main.Main;
import utilities.NoBulletSpawnerException;

public abstract class RangedEnemy extends Enemy {

	protected BulletSpawner bulletSpawner;
	protected BulletPattern bulletPattern;
	protected int bulletSpeed;
	protected int bulletRadius;
	protected int bulletPower;
	
	public RangedEnemy(float x, float y, double angle, int speed, int radius) {
		super(x, y, angle, speed, radius);
		// TODO Auto-generated constructor stub
		this.bulletSpeed = Bullet.DEFAULT_SPEED;
		this.bulletRadius = Bullet.DEFAULT_RADIUS;
		this.bulletPower = 1;
	}
	
	public void spawnBullet(BulletPattern pattern) throws NoBulletSpawnerException{
		if(this.bulletSpawner == null){
//			System.out.println(this.bulletSpawner.isAlive());
			throw new NoBulletSpawnerException(this, pattern);
		}
		try{
			this.bulletSpawner.start();
			Main.logic.addThreadHolder(this.bulletSpawner);
		} catch (IllegalThreadStateException e){
			if(!this.bulletSpawner.isAlive()) throw new NoBulletSpawnerException(this, pattern);
		}	
	};
	
	public int getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	public int getBulletRadius() {
		return bulletRadius;
	}

	public void setBulletRadius(int bulletRadius) {
		this.bulletRadius = bulletRadius;
	}

	public int getBulletPower() {
		return bulletPower;
	}

	public void setBulletPower(int bulletPower) {
		this.bulletPower = bulletPower;
	}

	public BulletPattern getBulletPattern() {
		return bulletPattern;
	}

	public void setBulletPattern(BulletPattern bulletPattern) {
		this.bulletPattern = bulletPattern;
	}
	
	public void setNewBulletSpawner(BulletPattern pattern){
		this.bulletSpawner = new BulletSpawner(pattern);
	}

}
