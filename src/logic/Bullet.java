package logic;

import graphics.IRenderableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends Entity implements IRenderableObject {
	int power;
	Entity owner;
	public Bullet(float x, float y, double angle, int speed, int radius, Entity owner) {
		super(x, y, angle, speed, radius);
		this.power = 1;
		this.owner = owner;
	}
	@Override
	void update() {
		// TODO Auto-generated method stub
		if(!this.isDestroy()){
			move();
		}
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
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.RED);
		gc.setLineWidth(2);
		gc.fillOval(this.getX(), this.getY(), this.getRadius()*2, this.getRadius()*2);
		gc.strokeOval(this.getX(), this.getY(), this.getRadius()*2, this.getRadius()*2);
		gc.setFill(Color.BLACK);
		gc.fillText(Double.toString(this.getAngle()), this.getX(), this.getY());

	}

}
