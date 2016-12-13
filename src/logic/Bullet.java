package logic;

import graphics.IRenderableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends Entity implements IRenderableObject {

	private Entity owner;
	private int power;
	public static final int DEFAULT_SPEED = 5;
	static final int DEFAULT_RADIUS = 10;
	
	public Bullet(float x, float y, double angle, Entity owner) {
		super(x, y, angle, DEFAULT_SPEED, DEFAULT_RADIUS);
		this.power = 1;
		this.owner = owner;
	}
	
	public Bullet(float x, float y, double angle, int speed, int radius, int power, Entity owner) {
		super(x, y, angle, speed, radius);
		this.speed = speed;
		this.radius = radius;
		this.power = power;
		this.owner = owner;
	}
	
	public Entity getOwner() {
		return owner;
	}

	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		if(!this.isDestroy()){
			move();
		}
	}
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return this.isDestroy();
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public synchronized int getZ() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}
	@Override
	public synchronized void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.RED);
		if(this.owner instanceof Player){
			gc.setStroke(Color.BLUE);
		}	
		gc.setLineWidth(2);
		gc.fillOval(this.getX() - this.getRadius(), this.getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2);
		gc.strokeOval(this.getX() - this.getRadius(), this.getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2);

	}
}
