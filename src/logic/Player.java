package logic;

import bulletSpawner.BulletPattern;
import bulletSpawner.BulletSpawner;
import bulletSpawner.PlayerSkill;
import bulletSpawner.SpreadPattern;
import graphics.DrawingUtility;
import graphics.IRenderableHolder;
import graphics.IRenderableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import main.Main;
import utilities.*;

public class Player extends CollidableEntity implements IRenderableObject{
	
	private static BulletPattern playerPattern;
	private static BulletPattern PLAYER_DEFAULT_PATTERN;
	private static BulletPattern PLAYER_BUFFED_PATTERN;
	private static BulletSpawner playerBulletSpawner;
	private static int hp, firingDelay, exp, blinkCounter, blinkDuration = 0;
	private static boolean isImmune, isHit, isVisible;
	private static boolean isBerserk;
	public static final int DEFAULT_SPEED = 8;
	public static final int FOCUS_SPEED = 3;
		
	public Player(float x, float y, double angle) {
		super(x, y, angle, Player.DEFAULT_SPEED, 10);
		Player.PLAYER_DEFAULT_PATTERN = new SpreadPattern(this, 3, 10, 3, 3000, BulletPattern.DEFAULT_BURST_DELAY);
//		Player.PLAYER_BUFFED_PATTERN = new SpreadPattern(this, 5, 30, 1, 0, 50);
		Player.PLAYER_BUFFED_PATTERN = new BulletPattern(this, 1, 25, 50) {
			
			@Override
			public void spawnBullet() {
				// TODO Auto-generated method stub
				try {
					for(int i = 0; i < 5; i++){
						for(double angle = - Math.PI / 12; angle <= Math.PI / 12; angle += Math.PI / 48 ){
							Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), owner.getAngle() + angle, 10, 5, 1, owner));
						}
						Thread.sleep(50);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}
			
		};
		Player.playerPattern = PLAYER_DEFAULT_PATTERN;
		Player.playerBulletSpawner = new BulletSpawner(playerPattern);
		Player.firingDelay = 6000/3;
		Player.hp = 3;
		Player.isImmune = false;
		Player.blinkDuration = 0;
		Player.blinkCounter = 10;
		Player.isVisible = true;
		Player.isHit = false;
		Player.exp = 0;
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
			if(!playerBulletSpawner.isAlive()){
				playerBulletSpawner = new BulletSpawner(playerPattern);
			}
//			System.out.println("new bullet spawner");
			try{
				playerBulletSpawner.start();
				Main.logic.addThreadHolder(playerBulletSpawner);
			} catch (Exception e){
				if(!playerBulletSpawner.isAlive()){
					Player.playerBulletSpawner = new BulletSpawner(playerPattern);
				}
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
//				System.out.println("blinking out");
			}
			if(blinkCounter <= 5){
				isVisible = true;
//				System.out.println("blinking in");

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
			
			if(InputUtility.getKeyTriggered(KeyCode.Q)){
				PlayerSkill.SKILL_1.useSkill();
			}
			
			if(InputUtility.getKeyTriggered(KeyCode.E)){
				PlayerSkill.SKILL_2.useSkill();
			}
			
			if(InputUtility.getKeyTriggered(KeyCode.SPACE)){
				PlayerSkill.SKILL_3.useSkill();
			}
			
			if(InputUtility.getKeyTriggered(KeyCode.R)){
				PlayerSkill.SKILL_4.useSkill();
			}
			
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
			
			this.setAngle(getMouseFocusingAngle(this, InputUtility.getMouseX(), InputUtility.getMouseY()));
//			System.out.println(this.getAngle());
			
			if(InputUtility.isMouseLeftDown()){
//				DrawingUtility.flashScreen(((Canvas) Main.gameScreen.getChildren().get(0)).getGraphicsContext2D());
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
		DrawingUtility.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.playerModel);
		if(Player.isBerserk){
			DrawingUtility.drawAura(gc, this.getX(), this.getY(), (float) (IRenderableHolder.playerAura.getHeight() * Math.sqrt(2)));
		}
	}
	public static void setNewBulletSpawner() {
		// TODO Auto-generated method stub
		Player.playerBulletSpawner = new BulletSpawner(Player.playerPattern);
	}
	
	public static void setBulletPattern(BulletPattern pattern) {
		// TODO Auto-generated method stub
		Player.playerPattern = pattern;
	}
	
	public static double getMouseFocusingAngle(Entity e, float x, float y){
		return Math.atan2(y - e.getY() , x - e.getX());
	}
	
	public static void setPlayerDefaultPattern(){
		Player.playerPattern = Player.PLAYER_DEFAULT_PATTERN;
	}
	
	public static void berserk(Boolean isBerserk){
		Player.isBerserk = isBerserk;
		if(Player.isBerserk)
			Player.setBulletPattern(PLAYER_BUFFED_PATTERN);
		else
			Player.setBulletPattern(PLAYER_DEFAULT_PATTERN);
		Player.playerPattern = Player.PLAYER_DEFAULT_PATTERN;
	}
}
