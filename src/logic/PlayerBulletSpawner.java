package logic;

import main.Main;

public class PlayerBulletSpawner extends BulletSpawner {
	
	public static PlayerBulletSpawner playerBulletSpawner = new PlayerBulletSpawner();
	
	public PlayerBulletSpawner() {
		// TODO Auto-generated constructor stub
		super(new Runnable(){

			@Override
			public void run()  {
				// TODO Auto-generated method stub
					try {
						playerBulletSpawner.spawnBullet(1, 3);
						Thread.sleep(Main.logic.getPlayer().getFiringDelay());
						playerBulletSpawner = new PlayerBulletSpawner();
					} catch (InterruptedException e){
						System.out.println("firing cooldown");
					}
			}
			
		}, Main.logic.getPlayer());
		
	}

	protected void spawnBullet(int dmg, int wave) throws InterruptedException {
		// TODO Auto-generated method stub
		for(int i = 0; i < wave; i++){
			Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle(), Bullet.DEFAULT_SPEED, 10, dmg, owner));
			Thread.sleep(100);
			System.out.println("SHOOT!");
		}
	}

}
