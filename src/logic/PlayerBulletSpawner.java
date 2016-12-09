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
						PlayerBulletSpawner.spawnBullet(Main.logic.getPlayer().getX(), Main.logic.getPlayer().getY(), Main.logic.getPlayer().getAngle(), 1, 3);
						Thread.sleep(Player.getFiringDelay());
						playerBulletSpawner = new PlayerBulletSpawner();
					} catch (InterruptedException e){
						System.out.println("firing cooldown");
					}
			}
			
		});
		
	}

	public static void spawnBullet(float x, float y, double angle, int dmg, int wave) throws InterruptedException {
		// TODO Auto-generated method stub
		for(int i = 0; i < wave; i++){
			Main.logic.addNewObject(new Bullet(Main.logic.getPlayer().getX(), Main.logic.getPlayer().getY(), Main.logic.getPlayer().getAngle(), Main.logic.getPlayer().getSpeed(), 10, Main.logic.getPlayer()));
			Thread.sleep(100);
			System.out.println("SHOOT!");
		}
	}

}
