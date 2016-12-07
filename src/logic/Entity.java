package logic;

public abstract class Entity {
	int hp;
	int x,y,directionX,directionY;
	int speed;
	boolean isDestroy;
	public Entity(int hp, int x, int y, int directionX, int directionY, int speed) {
		super();
		this.hp = hp;
		this.x = x;
		this.y = y;
		this.directionX = directionX;
		this.directionY = directionY;
		this.speed = speed;
		this.isDestroy = false;
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirectionX() {
		return directionX;
	}
	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}
	public int getDirectionY() {
		return directionY;
	}
	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}
	public boolean isDestroy() {
		return isDestroy;
	}
	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}
	public int getSpeed() {
		return speed;
	}
	
	
}
