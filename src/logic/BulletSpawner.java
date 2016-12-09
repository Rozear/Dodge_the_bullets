package logic;

public abstract class BulletSpawner extends Thread {
	
	public BulletSpawner(Runnable runnable){
		super(runnable);
	}

}
