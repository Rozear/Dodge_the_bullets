package logic;

import logic.InputUtility;
import javafx.scene.input.KeyCode;

public class Player extends Entity{

	static int hp, firingDelay;

	private static int angle = 0; // angle 0 = EAST
	public Player(float x, float y, float directionX, float directionY, int speed) {
		super(x, y, directionX, directionY, speed);
		Player.firingDelay = 9000000/3;
		Player.hp = 3;
		this.radius = 20;
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
		} else {
			angle += 3;
			if (angle >= 360)
				angle -= 360;
		}
	}
	
	@Override
	void update() {
		if(!isDestroy){
			//check collide
			//if(this.collideWith(other))
			//		this.hit(other);
			
			if (InputUtility.getKeyPressed(KeyCode.W)) {
				forward();
			}
			if (InputUtility.getKeyPressed(KeyCode.A)) {
				turn(true);
			} else if (InputUtility.getKeyPressed(KeyCode.D)) {
				turn(false);
			}
		}
		
	}

}
