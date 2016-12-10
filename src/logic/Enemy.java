package logic;

import graphics.DrawingUtil;
import graphics.IRenderableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.IRenderableHolder;

public class Enemy extends CollidableEntity implements IRenderableObject {

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
				((Player) ((Bullet) e).getOwner()).setExp(((Player) ((Bullet) e).getOwner()).getExp() + this.getGivenExp());
				this.setHp(this.getHp() - ((Bullet) e).getPower());
				if(this.getHp()<=0)
					this.setDestroy(true);
				e.setDestroy(true);
			}
		}
		else if(e instanceof Player){
			this.setDestroy(true);
			((Player) e).setHp(((Player) e).getHp() - 1);
		}
	}

	@Override
	void update() {
		if(!this.isDestroy()){
			move();
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

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtil.drawAvatarBox(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar);
		DrawingUtil.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.enemyAvatar);
		DrawingUtil.drawHitBox(gc, this.getX(), this.getY(), this.getRadius(), Color.RED);
	}
}
