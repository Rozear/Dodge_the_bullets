package logic;

public class NormalBulletSpawner extends BulletSpawner {

	public NormalBulletSpawner(RangedEnemy owner, int dmg, long cd) {
		super(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
					try {
						Thread.sleep(cd);
						BulletPattern.NORMAL_FIRE(owner);
					} catch (InterruptedException e) {
							// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		}, owner);
	}

}
