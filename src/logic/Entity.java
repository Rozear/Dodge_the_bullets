package logic;

public abstract class Entity {
	float x,y;
	float nextX,nextY;
	float directionX,directionY;
	int speed;
	boolean isDestroy;
	int radius=10;
	public Entity(float x, float y, float directionX, float directionY, int speed) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.directionX = directionX;
		this.directionY = directionY;
		this.speed = speed;
		this.isDestroy = false;
		this.nextX = x+speed*directionX;
		this.nextY = y+speed*directionY;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
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
	public void calculateNextState(){
		
		//if this != bullet => calculate directionX,Y
		if(x+speed*directionX >= 0)
			this.nextX = x+speed*directionX;
		else
			this.nextX = 0;
		if(y+speed*directionY >= 0)
			this.nextY = y+speed*directionY;
		else 
			this.nextY = 0;
		
		
	}
	
	public void move(){
		this.calculateNextState();
		this.x = this.nextX;
		this.y = this.nextY;
		}
	
	protected boolean collideWith(Entity other){
		return Math.hypot(this.x-other.x, this.y-other.y) <= this.radius+other.radius;
	}
	
	abstract  void update();
}
