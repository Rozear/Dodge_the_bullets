package logic;

import javafx.scene.input.KeyCode;
import utilities.*;

public class Player extends Entity {
	
	private static int hp, firingDelay;
	static int DEFAULT_SPEED = 10;
	
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
		} else {
			angle += 3;
			if (angle >= 360)
				angle -= 360;
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
