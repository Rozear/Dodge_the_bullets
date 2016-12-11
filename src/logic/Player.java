package logic;

import graphics.DrawingUtil;
import graphics.IRenderableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import main.IRenderableHolder;
import main.Main;
import utilities.*;

public class Player extends CollidableEntity implements IRenderableObject{
	
	private static int hp, firingDelay, exp, blinkCounter, blinkDuration = 0;
	private static boolean isImmune, isHit, isVisible;
	public static final int DEFAULT_SPEED = 8;
	public static final int FOCUS_SPEED = 3;
		
	public Player(float x, float y, double angle) {
		super(x, y, angle, Player.DEFAULT_SPEED, 10);
		Player.firingDelay = 6000/3;
		Player.hp = 3;
		Player.isImmune = false;
		Player.blinkDuration = 0;
		Player.blinkCounter = 10;
		Player.isVisible = true;
		Player.isHit = false;
		Player.exp = 0;
		System.out.println("PLAYER ADDED");
	}
	public boolean isHit() {
		return isHit;
	}
	public void setHit(boolean isHit) {
		Player.isHit = isHit;
	}
	public boolean isImmune() {
		return isImmune;
	}
	public void setImmune(boolean isImmune) {
		Player.isImmune = isImmune;
	}
	public int getHp() {
		return Player.hp;
	}
	public void setHp(int hp) {
		Player.hp = hp;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		Player.exp = exp;
	}
	public int getFiringDelay() {
		return Player.firingDelay;
	}
	
	
	public void hit(Entity e){
		
		
		if(e instanceof Bullet){
			if(!(((Bullet) e).getOwner() instanceof Player)){
				if(!Player.isImmune){
					this.setHp(this.getHp() - ((Bullet) e).getPower());
					this.setHit(true);
				}	
				e.setDestroy(true);
			}
		}		
		else if( e instanceof Enemy){
			if(!Player.isImmune){
				this.setHp(this.getHp() - 1);
				this.setHit(true);
			}	
			e.setDestroy(true);
		}
		
		if(this.getHp()<=0)
			this.setDestroy(true);
	}
	
	public void shoot() {
		// TODO Auto-generated method stub
		if(Main.logic.getPlayer().isDestroy()) return;
		try{
			PlayerBulletSpawner.playerBulletSpawner.start();	
		} catch (Exception e){
			System.out.println("firing cooldown");
		}
		
	}
	
	public void checkIsHit() {
		// TODO Auto-generated method stub
		if(isHit){
			isImmune = true;
			blinkDuration = 150;
			Player.blinkCounter = 10;
			System.out.println("immune");
			isHit = false;
		}
	}
	
	@Override
	void update() {
		
		if (blinkDuration > 0) {
			if(blinkCounter > 5){
				isVisible = false;
				System.out.println("blinking out");
			}
			if(blinkCounter <= 5){
				isVisible = true;
				System.out.println("blinking in");

			}
			if(blinkCounter == 0){
				blinkCounter = 10;
			} else {
				blinkCounter--;
			}
			blinkDuration--;
			if(blinkDuration == 0){
				isVisible = true;
				isImmune = false;
			}
		}
		
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
			if(this.getX() + this.getRadius() > Configuration.ARENA_WIDTH) this.setX(Configuration.ARENA_WIDTH - this.getRadius());
			if(this.getY() - this.getRadius() < 0) this.setY(0 + this.getRadius());
			if(this.getY() + this.getRadius() > Configuration.ARENA_HEIGHT) this.setY(Configuration.ARENA_HEIGHT - this.getRadius());
			
			this.setAngle(PositioningUtil.getMouseFocusingAngle(this, InputUtility.getMouseX(), InputUtility.getMouseY()));
//			System.out.println(this.getAngle());
			
			if(InputUtility.isMouseLeftDown()){
				this.shoot();
			}
			
			if(InputUtility.getKeyPressed(KeyCode.SHIFT)){
				this.setSpeed(FOCUS_SPEED);
			} else {
				this.setSpeed(DEFAULT_SPEED);
			}
			
		}
		
	}
	
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return this.isDestroy();
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtil.drawAvatarBox(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.playerAvatar);
		DrawingUtil.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.playerAvatar);
		DrawingUtil.drawHitBox(gc, this.getX(), this.getY(), this.getRadius(), Color.BLUE);
		DrawingUtil.drawHP(gc, this);
		if(isImmune){
			DrawingUtil.drawHitBox(gc, this.getX(), this.getY(), this.getRadius(), Color.RED);
		}
	}
	
}
