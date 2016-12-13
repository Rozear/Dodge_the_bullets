package bulletSpawner;

import logic.Bullet;
import logic.Entity;
import main.Main;

public final class SpreadPattern extends BulletPattern {

	int lines;
	double totalAngle;

	public SpreadPattern(Entity owner, int lines, double totalAngle, int wave, long cd, long burstDelay) {
		// TODO Auto-generated constructor stub
		super(owner, wave, cd, burstDelay);
		this.lines = lines;
		this.totalAngle = Math.toRadians(totalAngle) % (Math.PI * 2);
		;
	}

	@Override
	public void spawnBullet() {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < wave; i++) {
				if (totalAngle == 0) {
					for (double angle = 0; angle < Math.PI * 2; angle += Math.PI * 2 / (lines)) {
						Main.logic
								.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle() + angle, owner));
					}
				} else {
					for (double angle = -totalAngle / 2; angle <= totalAngle / 2; angle += totalAngle / (lines - 1)) {
						Main.logic
								.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle() + angle, owner));
					}
				}
				Thread.sleep(burstDelay);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

}
