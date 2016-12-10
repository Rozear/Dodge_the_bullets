package logic;

import main.Main;

public class BulletPattern {

	public synchronized static final void NORMAL_FIRE(RangedEnemy owner){						
		Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle(), owner.getBulletSpeed(), owner.getBulletRadius(), owner.getBulletPower(), owner));
	}

	public synchronized static final void BURST_FIRE(RangedEnemy owner, int wave) throws InterruptedException {
		for(int i = 0; i < wave; i++){
			Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle(), owner.getBulletSpeed(), owner.getBulletRadius(), owner.getBulletPower(), owner));
			Thread.sleep(80);
			System.out.println("SHOOT!");
		}
	}
	
	public synchronized static final void SPREAD_FIRE(RangedEnemy owner, int lines, double totalAngle, int wave) throws InterruptedException{
		totalAngle = (Math.toRadians(totalAngle) % (Math.PI * 2));
		for(int i = 0; i < wave; i++){
			if(totalAngle == 0){
				for(double angle = 0; angle < Math.PI * 2; angle += Math.PI * 2 / (lines)){
					Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle() + angle, owner.getBulletSpeed(), owner.getBulletRadius(), owner.getBulletPower(), owner));
					System.out.println("SHOOT!");
				}
			}
			else{
				for(double angle = -totalAngle / 2; angle <= totalAngle/2; angle += totalAngle / (lines - 1)){
					Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle() + angle, owner.getBulletSpeed(), owner.getBulletRadius(), owner.getBulletPower(), owner));
					System.out.println("SHOOT!");
				}
			}
			Thread.sleep(80);
		}

	}

}
