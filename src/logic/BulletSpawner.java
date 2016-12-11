package logic;

public abstract class BulletSpawner extends Thread {
		
	Entity shooter;
	
	public BulletSpawner( Runnable runnable, Entity shooter){
		super(runnable);
		this.shooter = shooter;
	}
	
	public Entity getShooter(){
		return this.shooter;
	}
	
}
