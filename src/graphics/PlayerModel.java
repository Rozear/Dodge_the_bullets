package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameLogic;
import logic.Player;

public class PlayerModel implements IRenderableObject {

	private Player player = GameLogic.player;
	
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
		System.out.println("player rendering");
		gc.setFill(Color.BLACK);
		System.out.println("color set");

		gc.rotate(GameLogic.player.getAngle());
		System.out.println("rotate set");

		gc.fillRect(player.getX() - 50, player.getY() - 50, 100, 100);
		System.out.println("player rendered");
	}

}
