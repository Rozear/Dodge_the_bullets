package logic;

import graphics.IRenderableObject;
import main.Main;

public abstract class Enemy extends CollidableEntity implements IRenderableObject {

	protected int hp;
	protected int givenExp;
	
	public Enemy(float x, float y, double angle, int speed, int radius) {
		super(x, y, angle, speed, radius);
		// TODO Auto-generated constructor stub
		this.hp = 3;
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

	public void hit(Entity e){
//		if(e instanceof Bullet){
//			this.setHp(this.getHp() - ((Bullet) e).getPower());
//			if(this.getHp()<=0)
//				this.setDestroy(true);
//			e.setDestroy(true);
//			if(((Bullet) e).getOwner() instanceof Player){
//				((Player) ((Bullet) e).getOwner()).setExp(((Player) ((Bullet) e).getOwner()).getExp() + this.getGivenExp());
//			}
//		}
		if(e instanceof Bullet){
			if(((Bullet) e).getOwner() instanceof Player){
				this.setHp(this.getHp() - ((Bullet) e).getPower());
				if(this.getHp()<=0){
					this.setDestroy(true);
					((Player) ((Bullet) e).getOwner()).setExp(((Player) ((Bullet) e).getOwner()).getExp() + this.getGivenExp());
				}	
				e.setDestroy(true);
			}
		}
		else if(e instanceof Player){
			this.setDestroy(true);
			((Player) e).setHp(((Player) e).getHp() - 1);
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
		return 0;
	}
	
	public void focusOnPlayer(){
		this.setAngle(Math.atan2(Main.logic.getPlayer().getY() - this.getY(), Main.logic.getPlayer().getX() - this.getX()));
	}
}
