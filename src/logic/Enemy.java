package logic;

public class Enemy extends Entity{

	int hp;
	public Enemy(int x, int y, float directionX, float directionY, int speed) {
		super(x, y, directionX, directionY, speed);
		// TODO Auto-generated constructor stub
		this.hp = 3;
	}

}
