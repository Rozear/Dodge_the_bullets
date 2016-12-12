package logic;

import java.util.ArrayList;
import java.util.List;

import graphics.IRenderableObject;
import main.IRenderableHolder;
import utilities.Configuration;

public class GameLogic {
	
	
	private Player player;
	private List<Entity> gameObjectContainer;
	private List<Thread> threadHolder;
	
	private MobSpawner mobSpawner;
	
	public GameLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
		this.threadHolder = new ArrayList<Thread>();
//		Field field = new Field();
//		RenderableHolder.getInstance().add(field);
		this.player = new Player(Configuration.ARENA_WIDTH/2, Configuration.ARENA_HEIGHT/2, 30);
		addNewObject(player);
		mobSpawner = new MobSpawner();
		addThreadHolder(mobSpawner);
		mobSpawner.start();
	}
	
	public synchronized void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		IRenderableHolder.getInstance().add((IRenderableObject) entity);
	}
	
	public synchronized void logicUpdate(){
		PlayerSkill.updateSkill();
		for(int i = gameObjectContainer.size() - 1; i >= 0; i--){
			Entity e = gameObjectContainer.get(i);
//			System.out.println(e.getClass());
			e.update();
			for(int j = i - 1; j >= 0; j--){
				if(gameObjectContainer.get(j) instanceof CollidableEntity){
					if(((CollidableEntity) gameObjectContainer.get(j)).collideWith(e)){
						((CollidableEntity) gameObjectContainer.get(j)).hit(e);
					}
				}
			}
			this.getPlayer().checkIsHit();
			if(e.isOutOfBound()){
//				System.out.println("out");
				e.setDestroy(true);
			}
			if(e.isDestroy()){
				gameObjectContainer.remove(e);
//				if(e instanceof RangedEnemy){
//					((RangedEnemy) e).getBulletSpawner().interrupt();
//				}
			}
		}
		for(int i = threadHolder.size() - 1; i >= 0; i--){
			if(!threadHolder.get(i).isAlive()){
				threadHolder.remove(i);
			}
		}
	}
	
	public synchronized void addThreadHolder(Thread thread){
		System.out.println("thread added " + thread.getClass());
		threadHolder.add(thread);
	}
	
	public  void clearThreadHolder(){
		for(Thread thread : threadHolder){
			System.out.println("thread interrupt " + thread.getClass());
			thread.interrupt();
		}
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
}
