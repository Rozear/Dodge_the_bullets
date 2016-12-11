package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class GameAnimation implements IRenderableObject {

	private Image image = null;
	private int frameCount, frameDelay;
	private int currentFrame, frameDelayCount;
	private int x, y, frameWidth, frameHeight;
	private boolean visible = false, playing = false;

	public GameAnimation(Image shootanim, int frameCount, int frameDelay) {
		/* fill code */
		this.frameCount = frameCount;
		this.frameDelay = frameDelay;
		this.image = shootanim;
		this.currentFrame = 0;
		this.frameDelayCount = 0;
		this.x = 0;
		this.y = 0;
		if(image != null){
			this.frameHeight = (int) image.getHeight();
			this.frameWidth = (int) image.getWidth()/frameCount;
		}
		else{
			this.frameHeight = 0;
			this.frameWidth = 0;
		}
	}

	protected void topLeftAnimationAt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	protected void centerAnimationAt(int x, int y) {
		this.x = x - frameWidth / 2;
		this.y = y - frameHeight / 2;
	}

	public void play() {
		currentFrame = 0;
		playing = true;
		visible = true;
	}

	public void stop() {
		currentFrame = 0;
		playing = false;
		visible = false;
	}

	public void updateAnimation() {
		if (!playing)
			return;
		if (frameDelayCount > 0) {
			frameDelayCount--;
			return;
		}
		frameDelayCount = frameDelay;
		currentFrame++;
		if (currentFrame == frameCount) {
			stop();
		}
	}
	@Override
	public boolean isVisible() {
		/* fill code */
		return visible;
	}

	@Override
	public int getZ() {
		/* fill code */
		return Integer.MAX_VALUE-1;
	}

	@Override
	public void render(GraphicsContext gc) {
		/* fill code */
		if(playing && image != null){
			gc.drawImage(new WritableImage(image.getPixelReader(), (currentFrame)*frameWidth, 0, frameWidth, frameHeight), x, y);
		}
	}

}
