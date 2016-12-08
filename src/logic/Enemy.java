package logic;

public class Enemy extends Entity{

	int hp;
	int firingDelayCounter,firingDelay;
	
	public Enemy(float x, float y, float directionX, float directionY, int speed) {
		super(x, y, directionX, directionY, speed);
		// TODO Auto-generated constructor stub
		this.hp = 3;
		this.firingDelay = 2;
		this.firingDelayCounter = this.firingDelay;
		this.radius = 20;
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void hit(Bullet b){
		this.setHp(this.getHp() - b.power);
		if(this.getHp()<=0)
			this.setDestroy(true);
		b.setDestroy(true);
	}

	@Override
	void update() {
		if(!isDestroy){
			if (firingDelayCounter > 0)
			{

					firingDelayCounter--;
	 				return;
	 		}
	 		firingDelayCounter = firingDelay;
	 		new Bullet(this.x, this.y, this.directionX, this.directionY, 10);
			move();	
		}
	}
}
