package logic;

public abstract class Entity {
	int x,y;
	float directionX,directionY;
	int speed;
	boolean isDestroy;

	public Entity(int x, int y, float directionX, float directionY, int speed) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.directionX = directionX;
		this.directionY = directionY;
		this.speed = speed;
		this.isDestroy = false;
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
	public float getDirectionX() {
		return directionX;
	}
	public void setDirectionX(float directionX) {
		this.directionX = directionX;
	}
	public float getDirectionY() {
		return directionY;
	}
	public void setDirectionY(float directionY) {
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
