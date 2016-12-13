package graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class IRenderableHolder {

	private static final IRenderableHolder instance = new IRenderableHolder();
	
	public static Image grassField;
	public static Image brickFloor;
	public static Image desert;
	public static Image heart;
	public static Image playerModel;
	public static Image witchModel;
	public static Image trollModel;
	public static Image wispModel;
	public static Image wispModelLeft;
	public static Image banditModel;
	public static Image playerAura;
	public static Image skill1_icon;
	public static Image skill2_icon;
	public static Image skill3_icon;
	public static Image skill4_icon;
	public static Media skill4_sound;
	public static Media skill1_sound;
	public static Media skill2_sound;
	public static Media skill3_sound;
	public static Media ouch;
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
		skill4_sound =  new Media(loader.getResource("media/skill4.mp3").toString());
		skill3_sound =  new Media(loader.getResource("media/skill3.mp3").toString());
		skill2_sound =  new Media(loader.getResource("media/skill2.mp3").toString());
		skill1_sound =  new Media(loader.getResource("media/skill1.mp3").toString());
		ouch =  new Media(loader.getResource("media/ouch1.mp3").toString());
		grassField = new Image(loader.getResource("bg/grass.png").toString());
		brickFloor = new Image(loader.getResource("bg/city.png").toString());
		desert = new Image(loader.getResource("bg/desert1.png").toString());	
		heart = new Image(loader.getResource("model/heart.png").toString());
		playerModel = new Image(loader.getResource("model/playerModel.png").toString());
		skill1_icon = new Image(loader.getResource("model/skill1_icon.png").toString());
		skill2_icon = new Image(loader.getResource("model/skill2_icon.png").toString());
		skill3_icon = new Image(loader.getResource("model/skill3_icon.png").toString());
		skill4_icon = new Image(loader.getResource("model/skill4_icon.png").toString());
		playerAura = new Image(loader.getResource("model/playerAura.png").toString());
	/*	
		witchModel = new Image(loader.getResource("model/witchModel.png").toString());
		trollModel = new Image(loader.getResource("model/trollModel.png").toString());
		wispModel = new Image(loader.getResource("model/wispModel.png").toString());
		wispModelLeft = new Image(loader.getResource("model/wispModelLeft.png").toString());
		banditModel = new Image(loader.getResource("model/banditModel.png").toString());
	*/
	}
	
	public static IRenderableHolder getInstance() {
		return instance;
	}
	
	public void addAndSort(IRenderableObject entity) {
		add(entity);
		sort();
	}
	
	public synchronized void add(IRenderableObject entity) {
		entities.add(entity);
		sort();
	}
	
	public synchronized void sort(){
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
