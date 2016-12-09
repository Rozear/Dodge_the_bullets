package logic;

import java.util.ArrayList;
import java.util.List;

import graphics.IRenderableObject;
import main.IRenderableHolder;
import utilities.Configuration;

public class GameLogic {
	
	
	private  Player player;
	private List<Entity> gameObjectContainer;
	
	public GameLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
	
//		Field field = new Field();
//		RenderableHolder.getInstance().add(field);
		this.player = new Player(Configuration.ARENA_WIDTH/2, Configuration.ARENA_HEIGHT/2, 30); 
		addNewObject(player);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		IRenderableHolder.getInstance().add((IRenderableObject) entity);
	}
	
	public synchronized void logicUpdate(){
		for(Entity e : gameObjectContainer){
			e.update();
			if(e.isOutOfBound()){
				e.setDestroy(true);
				gameObjectContainer.remove(e);
			}
		}
	}
	
	public Player getPlayer(){
		return this.player;
	}
}
