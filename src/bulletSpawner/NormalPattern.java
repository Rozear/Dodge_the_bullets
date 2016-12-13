package bulletSpawner;

import logic.Bullet;
import logic.Entity;
import main.Main;

public final class NormalPattern extends BulletPattern {

	public NormalPattern(Entity owner, int wave, long cd, long burstDelay) {
		// TODO Auto-generated constructor stub
		super(owner, wave, cd, burstDelay);
	}

	@Override
	public void spawnBullet() {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < wave; i++) {
				Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle(), owner));
				Thread.sleep(burstDelay);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

}
