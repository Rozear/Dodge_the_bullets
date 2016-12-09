package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;

public class PlayerModel implements IRenderableObject {
	
	private Image playerAvatar = new Image(ClassLoader.getSystemResource("test/blueTriangle.png").toString());
	
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
		DrawingUtil.drawRotateAvatar(gc, GameLogic.player.getX(), GameLogic.player.getY(), GameLogic.player.getAngle(), playerAvatar);
//		DrawingUtil.drawRotateAvatar(gc, GameLogic.player.getX(), GameLogic.player.getY(), GameLogic.player.getAngle());
//		System.out.println("player rendered");
	}

}
