package graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;

public class IRenderableHolder {

	private static final IRenderableHolder instance = new IRenderableHolder();
	
	public static Image grassField;
	public static Image brickFloor;
	public static Image desert;
	public static Image playerAvatar;
	public static Image enemyAvatar1;
	public static Image enemyAvatar2;
	public static Image enemyAvatar3;
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
		grassField = new Image(loader.getResourceAsStream("bg/grassTile.png"));
		brickFloor = new Image(loader.getResourceAsStream("bg/city.png"));
		desert = new Image(loader.getResourceAsStream("bg/desert1.png"));		
		playerAvatar = new Image(loader.getResourceAsStream("test/redTriangle.png"));
		enemyAvatar1 = new Image(loader.getResourceAsStream("test/blueTriangle.png"));
		enemyAvatar2 = new Image(loader.getResourceAsStream("test/orangeTriangle.png"));
		enemyAvatar3 = new Image(loader.getResourceAsStream("test/purpleTriangle.png"));
		heart = new Image(loader.getResourceAsStream("model/heart.png"));
		playerModel = new Image(loader.getResourceAsStream("model/playerModel.png"));
		witchModel = new Image(loader.getResourceAsStream("model/WitchModel.png"));
		trollModel = new Image(loader.getResourceAsStream("model/trollModel.png"));
		wispModel = new Image(loader.getResourceAsStream("model/wispModel.png"));
		wispModelLeft = new Image(loader.getResourceAsStream("model/wispModelLeft.png"));
		banditModel = new Image(loader.getResourceAsStream("model/BanditModel.png"));
		playerAura = new Image(loader.getResourceAsStream("model/playerAura.png"));
		skill1_icon = new Image(loader.getResourceAsStream("model/skill1_icon.png"));
		skill2_icon = new Image(loader.getResourceAsStream("model/skill2_icon.png"));
		skill3_icon = new Image(loader.getResourceAsStream("model/skill3_icon.png"));
		skill4_icon = new Image(loader.getResourceAsStream("model/skill4_icon.png"));
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
