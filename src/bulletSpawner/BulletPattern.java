package bulletSpawner;

import logic.Entity;

public abstract class BulletPattern implements Runnable {

	public static final int DEFAULT_BURST_DELAY = 150;
	protected Entity owner;
	int wave;
	long cd, burstDelay;

	public BulletPattern(Entity owner, int wave, long cd, long burstDelay) {
		super();
		this.owner = owner;
		this.wave = wave;
		this.cd = cd;
		this.burstDelay = burstDelay;
	}

	public void run() {
		try {
			spawnBullet();
			Thread.sleep(this.cd);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

	public abstract void spawnBullet();

	public Entity getOwner() {
		return this.owner;
	}

	public void setBurstDelay(long burstDelay) {
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
