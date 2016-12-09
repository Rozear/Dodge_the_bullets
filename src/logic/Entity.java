package logic;

import utilities.Configuration;

public abstract class Entity {
	private float x,y;
	private float nextX;
	private float nextY;
	private double angle;
	private int speed;
	private boolean isDestroy;
	private int radius;
	
	public Entity(float x, float y, double angle, int speed, int radius) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.radius = radius;
		this.isDestroy = false;
		this.nextX = (float) (x+speed*Math.cos(angle));
		this.nextY = (float) (y+speed*Math.sin(angle));
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
	public boolean isDestroy() {
		return isDestroy;
	}
	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}
	public int getSpeed() {
		return speed;
	}
	public float getNextX() {
		return nextX;
	}
	public void setNextX(float nextX) {
		this.nextX = nextX;
	}
	public float getNextY() {
		return nextY;
	}
	public void setNextY(float nextY) {
		this.nextY = nextY;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void calculateNextState(){
		
		//if this != bullet => calculate directionX,Y
//		if(x+speed*Math.cos(angle) >= 0)
//			this.nextX = (float) (x+speed*Math.cos(angle));
//		else
//			this.nextX = 0;
//		if(y+speed*Math.sin(angle) >= 0)
//			this.nextY = (float) (y+speed*Math.sin(angle));
//		else 
//			this.nextY = 0;
		this.setNextX((float) (this.getX() + speed*Math.cos(angle)));
		this.setNextY((float) (this.getY() + speed*Math.sin(angle)));
		
		
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
	
	public boolean isOutOfBound(){
		if(this.getX() + this.getRadius() < 0 || this.getX() - this.getRadius() > Configuration.SCREEN_WIDTH){
			return true;
		}
		if(this.getY() + this.getRadius() < 0 || this.getY() - this.getRadius() > Configuration.SCREEN_HEIGHT){
			return true;
		}
		return false;
	}
}
