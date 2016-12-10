package logic;

import main.Main;

public class NormalBulletSpawner extends BulletSpawner {

	public NormalBulletSpawner(Runnable runnable, Entity owner) {
		super(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(1000);
						if(owner.isDestroy()){
							break;
						}
						Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle(), Bullet.DEFAULT_SPEED, 10, 1, owner));
					} catch (InterruptedException e) {
							// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}, owner);
		// TODO Auto-generated constructor stub
	}

}
