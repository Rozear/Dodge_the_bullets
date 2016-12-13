package bulletSpawner;

import logic.Entity;

public abstract class BulletPattern implements Runnable {

	static final int DEFAULT_BURST_DELAY = 150;
	Entity owner;
	int wave;
	long cd, burstDelay;
	
	public BulletPattern(Entity owner, int wave, long cd, long burstDelay) {
		super();
		this.owner = owner;
		this.wave = wave;
		this.cd = cd;
		this.burstDelay = burstDelay;
	}

	public void run(){
		try {
			spawnBullet();
//			System.out.println("spawn bullet");
			Thread.sleep(this.cd);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public abstract void spawnBullet();
	
	public Entity getOwner(){
		return this.owner;
	}
	
	public void setBurstDelay(long burstDelay){
		this.burstDelay = burstDelay;
	}

	public long getCd() {
		return cd;
	}

	public void setCd(long cd) {
		this.cd = cd;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

}
