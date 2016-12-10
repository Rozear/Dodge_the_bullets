package logic;

import main.Main;

public class PlayerBulletSpawner extends BulletSpawner {

	public static PlayerBulletSpawner playerBulletSpawner = new PlayerBulletSpawner();
	
	
	public PlayerBulletSpawner() {
		super(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					spawnBullet(3, 10, 1, 3);
					Thread.sleep(Main.logic.getPlayer().getFiringDelay());
					playerBulletSpawner = new PlayerBulletSpawner();
				} catch (InterruptedException e){
					System.out.println("firing cooldown");
				}
			}
			
//			public synchronized void spawnBullet(int lines, double totalAngle, int dmg, int wave) throws InterruptedException{
//				totalAngle = (Math.toRadians(totalAngle) % (Math.PI * 2));
//				for(int i = 0; i < wave; i++){
//					if(totalAngle == 0){
//						for(double angle = 0; angle < Math.PI * 2; angle += Math.PI * 2 / (lines)){
//							Main.logic.addNewObject(new Bullet(Main.logic.getPlayer().getX(), Main.logic.getPlayer().getY(), Main.logic.getPlayer().getAngle() + angle, Bullet.DEFAULT_SPEED, 7, dmg, Main.logic.getPlayer()));
//							System.out.println("SHOOT!");
//						}
//					}
//					else{
//						for(double angle = -totalAngle / 2; angle <= totalAngle/2; angle += totalAngle / (lines - 1)){
//							Main.logic.addNewObject(new Bullet(Main.logic.getPlayer().getX(), Main.logic.getPlayer().getY(), Main.logic.getPlayer().getAngle() + angle, Bullet.DEFAULT_SPEED, 7, dmg, Main.logic.getPlayer()));
//							System.out.println("SHOOT!");
//						}
//					}
//					Thread.sleep(BulletPattern.BURST_DELAY);
//				}
//
//			}
			
			public synchronized void spawnBullet(int lines, double totalAngle, int dmg, int wave) throws InterruptedException{
				totalAngle = (Math.toRadians(totalAngle) % (Math.PI * 2));
				for(int i = 0; i < wave; i++){
					if(totalAngle == 0){
						for(double angle = 0; angle < Math.PI * 2; angle += Math.PI * 2 / (lines)){
							Main.logic.addNewObject(new Bullet(Main.logic.getPlayer().getX(), Main.logic.getPlayer().getY(), Main.logic.getPlayer().getAngle() + angle, Bullet.DEFAULT_SPEED, 10, dmg, Main.logic.getPlayer()));
							System.out.println("SHOOT!");
						}
					}
					else{
						for(double angle = -totalAngle / 2; angle <= totalAngle/2; angle += totalAngle / (lines - 1)){
							Main.logic.addNewObject(new Bullet(Main.logic.getPlayer().getX(), Main.logic.getPlayer().getY(), Main.logic.getPlayer().getAngle() + angle, Bullet.DEFAULT_SPEED, 10, dmg, Main.logic.getPlayer()));
							System.out.println("SHOOT!");
						}
					}
					Thread.sleep(BulletPattern.BURST_DELAY);
				}

			}
				
		}, Main.logic.getPlayer());
		// TODO Auto-generated constructor stub
	}

}
