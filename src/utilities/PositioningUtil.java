package utilities;

import logic.Entity;

public class PositioningUtil {
	
	public static double getFacingAngle(Entity e, float x, float y){
		return Math.atan2(y - e.getY() , x - e.getX());
	}
	
}
