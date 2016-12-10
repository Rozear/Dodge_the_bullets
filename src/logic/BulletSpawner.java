package logic;

public abstract class BulletSpawner extends Thread {
	
	Entity owner;
	
	public BulletSpawner(Runnable runnable, Entity owner){
		super(runnable);
		this.owner = owner;
	}
	
}
