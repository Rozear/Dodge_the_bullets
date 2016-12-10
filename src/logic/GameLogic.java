package logic;

import java.util.ArrayList;
import java.util.List;

import graphics.IRenderableObject;
import main.IRenderableHolder;
import utilities.Configuration;

public class GameLogic {
	
	
	private Player player;
	private List<Entity> gameObjectContainer;
	
	public GameLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
	
//		Field field = new Field();
//		RenderableHolder.getInstance().add(field);
		this.player = new Player(Configuration.ARENA_WIDTH/2, Configuration.ARENA_HEIGHT/2, 30);
		addNewObject(player);
		addNewObject(new RangedDummy(100, 100));
		new MobSpawner().start();
	}
	
	protected synchronized void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		IRenderableHolder.getInstance().add((IRenderableObject) entity);
	}
	
	public synchronized void logicUpdate(){
		for(int i = gameObjectContainer.size() - 1; i >= 0; i--){
			Entity e = gameObjectContainer.get(i);
			e.update();
			for(int j = i - 1; j >= 0; j--){
				if(gameObjectContainer.get(j) instanceof CollidableEntity){
					if(((CollidableEntity) gameObjectContainer.get(j)).collideWith(e)){
						((CollidableEntity) gameObjectContainer.get(j)).hit(e);
					}
				}
			}
			if(e.isOutOfBound()){
				System.out.println("out");
				e.setDestroy(true);
			}
			if(e.isDestroy()){
				gameObjectContainer.remove(e);
				if(e instanceof RangedEnemy){
					((RangedEnemy) e).getBulletSpawner().interrupt();
				}
			}
		}
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
}
