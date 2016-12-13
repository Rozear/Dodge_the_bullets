package logic;

public abstract class CollidableEntity extends Entity {

	public CollidableEntity(float x, float y, double angle, int speed, int radius) {
		super(x, y, angle, speed, radius);
		// TODO Auto-generated constructor stub
	}

	protected boolean collideWith(Entity e) {
		return Math.hypot(this.x - e.x, this.y - e.y) <= this.radius + e.radius;
	}

	public abstract void hit(Entity e);

}
