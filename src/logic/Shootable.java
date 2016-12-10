package logic;

public interface Shootable {
	
	public abstract void hit(Bullet b);
	
	public abstract boolean isShot(Bullet b);
}
