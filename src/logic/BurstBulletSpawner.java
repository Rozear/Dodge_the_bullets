package logic;

public class BurstBulletSpawner extends BulletSpawner {
		
	public BurstBulletSpawner(RangedEnemy owner, int dmg, int wave, int cd) {
		// TODO Auto-generated constructor stub
		super(new Runnable(){

			@Override
			public void run()  {
				// TODO Auto-generated method stub
						try {
							Thread.sleep(cd);
							BulletPattern.BURST_FIRE(owner, wave);
						} catch (InterruptedException e){
							System.out.println("firing cooldown");
						}
					
			}
			
		}, owner);
	}
	
}
