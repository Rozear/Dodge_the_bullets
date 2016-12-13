package logic;

import java.util.Random;

import graphics.IRenderableObject;
import main.Main;
import utilities.Configuration;

public abstract class Enemy extends CollidableEntity implements IRenderableObject {

	protected int hp;
	protected int givenExp;
	protected float pointX, pointY;
	protected boolean isAtPoint;
	private static int enemyCount = 0;
	
	public Enemy(float x, float y, double angle, int speed, int radius) {
		super(x, y, angle, speed, radius);
		// TODO Auto-generated constructor stub
		this.hp = 3;
		enemyCount++;
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getGivenExp() {
		return givenExp;
	}

	public static int getEnemyCount() {
		return enemyCount;
	}

	public void hit(Entity e){

		if(e instanceof Bullet){
			if(((Bullet) e).getOwner() instanceof Player){
				this.setHp(this.getHp() - ((Bullet) e).getPower());
				if(this.getHp()<=0){
					this.setDestroy(true);
					enemyCount--;
					((Player) ((Bullet) e).getOwner()).setExp(((Player) ((Bullet) e).getOwner()).getExp() + this.getGivenExp());
				}	
				e.setDestroy(true);
			}
		}
		else if(e instanceof Player){
			this.setDestroy(true);
			enemyCount--;
			if(!((Player) e).isImmune()){
				((Player) e).setHp(((Player) e).getHp() - 1);
				((Player) e).setHit(true);
			}
		}
		else if(e instanceof Enemy){
			double angle = Math.atan2(this.getY() - e.getY(), this.getX() - e.getX());
			x = (float) (e.getX() + (e.getRadius() + this.getRadius()) * Math.cos(angle));
			y = (float) (e.getY() + (e.getRadius() + this.getRadius()) * Math.sin(angle));
		}
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestroy();
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE - 1;
	}
	
	public void setNewPoint(){
		pointX = new Random().nextFloat() * Configuration.ARENA_WIDTH;
		pointY = new Random().nextFloat() * Configuration.ARENA_HEIGHT;
	}
	
	public void setNewPoint(float x, float y){
		pointX = x;
		pointY = y;
	}
	
	public void focusOnPlayer(){
		this.setAngle(Math.atan2(Main.logic.getPlayer().getY() - this.getY(), Main.logic.getPlayer().getX() - this.getX()));
	}
	
	public boolean walkTo(float x, float y){
		if(Math.abs(this.getX() - x) <= this.speed && Math.abs(this.getY() - y) <= this.speed){
			return true;
		}
		this.angle = Math.atan2(y - this.getY(), x - this.getX());
		move();
		return false;
	}
}
