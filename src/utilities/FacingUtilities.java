package utilities;

import logic.Entity;

public class FacingUtilities {
	
	public static double facingAngle(Entity e, float x, float y){
		return Math.toDegrees(Math.atan2(y - e.getY() , x - e.getX()));
	}
	
}
