package logic;

import graphics.DrawingUtil;
import graphics.IRenderableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.IRenderableHolder;
import utilities.*;

public class Player extends Entity implements IRenderableObject {
	
	private static int hp, firingDelay, firingCounter;
	static int DEFAULT_SPEED = 5;
		
	public Player(float x, float y, double angle) {
		super(x, y, angle, Player.DEFAULT_SPEED, 30);
		Player.firingDelay = 9000/3;
		Player.hp = 3;
		main.IRenderableHolder.getInstance().add(this);
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
	
	public void spawnBullet(){
		
	}
	
	@Override
	void update() {
		if(!this.isDestroy()){
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
			
			if(this.getX() - this.getRadius() < 0) this.setX(0 + this.getRadius());
			if(this.getX() + this.getRadius() > Configuration.SCREEN_WIDTH) this.setX(Configuration.SCREEN_WIDTH - this.getRadius());
			if(this.getY() - this.getRadius() < 0) this.setY(0 + this.getRadius());
			if(this.getY() + this.getRadius() > Configuration.SCREEN_HEIGHT) this.setY(Configuration.SCREEN_HEIGHT - this.getRadius());
			
			this.setAngle(PositioningUtil.getFacingAngle(this, InputUtility.getMouseX(), InputUtility.getMouseY()));
//			System.out.println(this.getAngle());
			
			if(InputUtility.isMouseLeftDown() && !PlayerBulletSpawner.playerBulletSpawner.isAlive()){
				try{
					PlayerBulletSpawner.playerBulletSpawner.start();
				} catch (Exception e){
					System.out.println("firing cooldown");
				}	
			}
						
		}
		
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtil.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.playerAvatar);
	}
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return this.isDestroy();
	}

}
