package logic;

import java.util.ArrayList;
import java.util.List;

import bulletSpawner.PlayerSkill;
import graphics.IRenderableHolder;
import graphics.IRenderableObject;
import utilities.Configuration;

public class GameLogic {

	private Player player;
	private List<Entity> gameObjectContainer;
	private List<Thread> threadHolder;

	private MobSpawner mobSpawner;

	public GameLogic() {
		this.gameObjectContainer = new ArrayList<Entity>();
		this.threadHolder = new ArrayList<Thread>();
		this.player = new Player(Configuration.ARENA_WIDTH / 2, Configuration.ARENA_HEIGHT / 2, 30);
		addNewObject(this.player);
		mobSpawner = new MobSpawner();
		addThreadHolder(mobSpawner);
		mobSpawner.start();
	}

	public synchronized void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		IRenderableHolder.getInstance().add((IRenderableObject) entity);
	}

	public synchronized void logicUpdate() {
		PlayerSkill.updateSkill();
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			Entity e = gameObjectContainer.get(i);
			e.update();
			for (int j = i - 1; j >= 0; j--) {
				if (gameObjectContainer.get(j) instanceof CollidableEntity) {
					if (((CollidableEntity) gameObjectContainer.get(j)).collideWith(e)) {
						((CollidableEntity) gameObjectContainer.get(j)).hit(e);
					}
				}
			}
			this.getPlayer().checkIsHit();
			if (e.isOutOfBound()) {
				e.setDestroy(true);
			}
			if (e.isDestroy()) {
				gameObjectContainer.remove(e);

			}
		}
		for (int i = threadHolder.size() - 1; i >= 0; i--) {
			if (!threadHolder.get(i).isAlive()) {
				threadHolder.remove(i);
			}
		}
	}

	public synchronized void addThreadHolder(Thread thread) {
		threadHolder.add(thread);
	}

	public void clearThreadHolder() {
		for (Thread thread : threadHolder) {
			thread.interrupt();
		}
	}

	public Player getPlayer() {
		return this.player;
	}

}
