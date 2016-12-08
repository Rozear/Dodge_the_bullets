package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;
import logic.Player;

public class PlayerModel implements IRenderableObject {

	private Player player = logic.Player.player;
	
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
		gc.setFill(Color.BLACK);
		gc.rotate(player.getAngle());
		gc.fillRect(player.getX() - 50, player.getY() - 50, 100, 100);
//		gc.rotate(Math.toDegrees(Math.atan((double) player.getDirectionY()/player.getDirectionX())));

	}

}
