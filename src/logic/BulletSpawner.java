package logic;

public abstract class BulletSpawner extends Thread {
	
	int dmg, wave, rate;
	
	public void spawnBullet(int dmg, int rate, int wave){
		super(new Runnable(){
				Thread.sleep(rate);
				spawnBullet();
		});
		this.dmg = dmg;
		this.wave = wave;
		this.rate = rate;
	}

	public abstract void spawnBullet();
	
}
