package utilities;

import bulletSpawner.BulletPattern;
import bulletSpawner.BulletSpawner;
import logic.Entity;
import logic.Player;
import logic.RangedEnemy;

public class NoBulletSpawnerException extends Exception {

	public NoBulletSpawnerException(RangedEnemy e, BulletPattern pattern) {
		// TODO Auto-generated constructor stub
		e.setNewBulletSpawner(pattern);
		System.out.println("create new spawner");
	}

}
