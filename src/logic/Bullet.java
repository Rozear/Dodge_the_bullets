package logic;

public class Bullet extends Entity {
	int power;
	public Bullet(int x, int y, float directionX, float directionY, int speed) {
		super(x, y, directionX, directionY, speed);
		// TODO Auto-generated constructor stub
		this.power = 1;
	}

}
