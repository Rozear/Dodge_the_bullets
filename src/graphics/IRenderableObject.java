package graphics;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderableObject {

	public abstract boolean isVisible();
	public abstract int getZ();
	public abstract void render(GraphicsContext gc);
	
}
