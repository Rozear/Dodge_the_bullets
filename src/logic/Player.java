package logic;

import javafx.scene.input.KeyCode;
import utilities.*;

public class Player extends Entity {
	
	private static int hp, firingDelay;
	static int DEFAULT_SPEED = 5;
	
	public Player(float x, float y, double angle) {
		super(x, y, angle, Player.DEFAULT_SPEED, 30);
		Player.firingDelay = 9000/3;
		Player.hp = 3;
		main.IRenderableHolder.getInstance().add(new graphics.PlayerModel());
		System.out.println("PLAYER ADDED");
	}
	public static int getHp() {
		return hp;
	}
	public static void setHp(int hp) {
		Player.hp = hp;
	}
	public static int getFiringDelay() {
		return firingDelay;
	}
	
	
	public void hit(Entity e){
		Player.setHp(Player.getHp() - 1);
		if(Player.getHp()<=0)
			this.setDestroy(true);
		
		if(e instanceof Bullet)
			e.setDestroy(true);
				
		else if( e instanceof Enemy){
			((Enemy) e).setHp(getHp() - 1);
			if(((Enemy) e).getHp()<=0)
				this.setDestroy(true);
		}
	}
	
	private void forward() {
		this.x += Math.cos(Math.toRadians(angle)) * speed;
		this.y += Math.sin(Math.toRadians(angle)) * speed;
	}

	private void turn(boolean left) {
		if (left) {
			angle -= 3;
			if (angle < 0)
				angle += 360;
			System.out.println("turn left");
		} else {
			angle += 3;
			if (angle >= 360)
				angle -= 360;
			System.out.println("turn right");

		}
	}
	
	public void spawnBullet(){
		
	}
	
	@Override
	void update() {
		if(!isDestroy){
			//check collide
			//if(this.collideWith(other))
			//		this.hit(other);
			float valueX = 0;
			float valueY = 0;
			
			
			if (InputUtility.getKeyPressed(KeyCode.W)) {
//				forward();
				valueY -= 1 ;
			}
			if (InputUtility.getKeyPressed(KeyCode.A)) {
//				turn(true);
				valueX -= 1;
			}
			if (InputUtility.getKeyPressed(KeyCode.D)) {
//				turn(false);
				valueX += 1;
			}
			if (InputUtility.getKeyPressed(KeyCode.S)) {
//				turn(false);
				valueY += 1;
			}
			
			if(valueX!=0&&valueY!=0){
				this.setX((float) (this.getX() + valueX * this.getSpeed()/Math.sqrt(2)));
				this.setY((float) (this.getY() + valueY * this.getSpeed()/Math.sqrt(2)));
			}
			else{
				this.setX(this.getX() + valueX * this.getSpeed());
				this.setY(this.getY() + valueY * this.getSpeed());
			}
			
			this.setAngle(FacingUtilities.facingAngle(this, InputUtility.getMouseX(), InputUtility.getMouseY()));
//			System.out.println(this.getAngle());
		}
		
	}

}
