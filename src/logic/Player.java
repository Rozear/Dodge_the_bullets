package logic;

public class Player extends Entity{

	int hp;
	public Player(int x, int y, float directionX, float directionY, int speed) {
		super(x, y, directionX, directionY, speed);
		this.hp = 10;
	}

}
