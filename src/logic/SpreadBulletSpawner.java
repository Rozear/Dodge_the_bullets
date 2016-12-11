package logic;

public class SpreadBulletSpawner extends BulletSpawner {

	int lines;
	double totalAngle;
	
	public SpreadBulletSpawner(RangedEnemy owner, int lines, double totalAngle, int dmg, int wave, int cd) {
		super(new Runnable(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
					try {
						Thread.sleep(cd);
						BulletPattern.SPREAD_FIRE(owner, lines, totalAngle, wave);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}		
			
		}, owner);

	}	
}
