package logic;

public class Bullet extends Entity {
	int power;
	public Bullet(float x, float y, float directionX, float directionY, int speed) {
		super(x, y, directionX, directionY, speed);
		this.power = 1;
	}
	@Override
	void update() {
		// TODO Auto-generated method stub
		if(!isDestroy)
			move();
		
	}

}
