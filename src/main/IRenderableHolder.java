package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import graphics.IRenderableObject;
import javafx.scene.image.Image;

public class IRenderableHolder {

	private static final IRenderableHolder instance = new IRenderableHolder();
	
	public static Image bg;
	public static Image playerAvatar;
	public static Image enemyAvatar;

	private List<IRenderableObject> entities;
	private Comparator<IRenderableObject> comparator;
	
	static{
		loadResource();
	}
	
	public IRenderableHolder() {
		entities = new ArrayList<IRenderableObject>();
		comparator = (IRenderableObject o1, IRenderableObject o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	
	public static void loadResource(){
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		bg = new Image(loader.getResourceAsStream("bg/grass.png"));
		playerAvatar = new Image(loader.getResourceAsStream("test/redTriangle.png"));
		enemyAvatar = new Image(loader.getResourceAsStream("test/blueTriangle.png"));
	}
	
	public static IRenderableHolder getInstance() {
		return instance;
	}
	
	public void addAndSort(IRenderableObject entity) {
		add(entity);
		sort();
	}
	
	public void add(IRenderableObject entity) {
		entities.add(entity);
		sort();
	}
	
	public void sort(){
		Collections.sort(entities, comparator);
	}
	
	
	public List<IRenderableObject> getEntities() {
		return entities;
	}

	
	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}
}
