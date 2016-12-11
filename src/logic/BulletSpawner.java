package logic;

public class BulletSpawner extends Thread {
		
	Entity shooter;
	
	public BulletSpawner(BulletPattern pattern){
		super(pattern);
		this.shooter = pattern.getOwner();
	}
	
}
