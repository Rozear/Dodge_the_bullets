package logic;

import graphics.DrawingUtil;
import graphics.IRenderableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import main.IRenderableHolder;
import utilities.*;

public class Player extends CollidableEntity implements IRenderableObject{
	
	private int hp, firingDelay, exp;
		
	public Player(float x, float y, double angle) {
		super(x, y, angle, Player.DEFAULT_SPEED, 15);
		this.firingDelay = 9000/3;
		this.hp = 3;
		main.IRenderableHolder.getInstance().add(this);
		System.out.println("PLAYER ADDED");
	}
	public int getHp() {
		return this.hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getFiringDelay() {
		return this.firingDelay;
	}
	
	
	public void hit(Entity e){
		
		
		if(e instanceof Bullet){
			if(!(((Bullet) e).getOwner() instanceof Player)){
				this.setHp(this.getHp() - ((Bullet) e).getPower());
				e.setDestroy(true);
			}
		}		
		else if( e instanceof Enemy){
			this.setHp(this.getHp() - 1);
			e.setDestroy(true);
		}
		
		if(this.getHp()<=0)
			this.setDestroy(true);
	}
	
	public void shoot() {
		// TODO Auto-generated method stub
		try{
			PlayerBulletSpawner.playerBulletSpawner.start();	
		} catch (Exception e){
			System.out.println("firing cooldown");
		}
		
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
			
			this.setAngle(PositioningUtil.getMouseFocusingAngle(this, InputUtility.getMouseX(), InputUtility.getMouseY()));
//			System.out.println(this.getAngle());
			
			if(InputUtility.isMouseLeftDown()){
				this.shoot();
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
		DrawingUtil.drawAvatarBox(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.playerAvatar);
		DrawingUtil.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.playerAvatar);
		DrawingUtil.drawHitBox(gc, this.getX(), this.getY(), this.getRadius(), Color.BLUE);
		DrawingUtil.drawHP(gc, this);
	}
	
	
	

}
