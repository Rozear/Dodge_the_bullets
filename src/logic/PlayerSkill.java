package logic;

import java.util.Random;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import graphics.IRenderableObject;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import main.IRenderableHolder;
import main.Main;
import utilities.Configuration;
import utilities.InputUtility;

public abstract class PlayerSkill extends Canvas{
	
	long skillCD, currentCD;
	Image skilIcon;
	boolean isMouseIn;
	static int textStartPosition = (Configuration.SCORE_WIDTH - Configuration.SKILL_FONT_SIZE) / 2;
	static FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
	static long time;
	
	public final static PlayerSkill SKILL_1 = new PlayerSkill(15000, IRenderableHolder.enemyAvatar1) {
		
		@Override
		public void applySkill() {
			// TODO Auto-generated method stub
			BulletSpawner circle = new BulletSpawner(new SpreadPattern(Main.logic.getPlayer(), 24, 360, 3, 0, 100));
			circle.start();
			Main.logic.addThreadHolder(circle);
		}
		
	};
	
	public final static PlayerSkill SKILL_2 = new PlayerSkill(15000, IRenderableHolder.enemyAvatar2) {
		
		BulletPattern pattern = new BulletPattern(Main.logic.getPlayer(), 25, 0, 25) {
			
			@Override
			public void spawnBullet() {
				// TODO Auto-generated method stub
				float x = owner.getX();
				float y = owner.getY();
				double angle = owner.getAngle();
				try {
					Main.logic.addNewObject(new Bullet(x, y, angle, owner));
					Main.logic.addNewObject(new Bullet(x, y, angle + Math.PI, owner));
					Main.logic.addNewObject(new Bullet(x, y, angle + Math.PI / 2, owner));
					Main.logic.addNewObject(new Bullet(x, y, angle - Math.PI / 2, owner));
					Thread.sleep(burstDelay);
					for(int i = 1; i <= wave; i++){
						Main.logic.addNewObject(new Bullet((float) (x + i * 8 * Math.sin(angle)), (float) (y - i * 8 * Math.cos(angle)), angle, owner));
						Main.logic.addNewObject(new Bullet((float) (x - i * 8 * Math.sin(angle)), (float) (y + i * 8 * Math.cos(angle)), angle, owner));
						Main.logic.addNewObject(new Bullet((float) (x + i * 8 * Math.sin(angle)), (float) (y - i * 8 * Math.cos(angle)), angle + Math.PI, owner));
						Main.logic.addNewObject(new Bullet((float) (x - i * 8 * Math.sin(angle)), (float) (y + i * 8 * Math.cos(angle)), angle + Math.PI, owner));
						Main.logic.addNewObject(new Bullet((float) (x + i * 8 * Math.sin(angle)), (float) (y + i * 8 * Math.cos(angle)), angle + Math.PI / 2, owner));
						Main.logic.addNewObject(new Bullet((float) (x - i * 8 * Math.sin(angle)), (float) (y - i * 8 * Math.cos(angle)), angle + Math.PI / 2, owner));
						Main.logic.addNewObject(new Bullet((float) (x - i * 8 * Math.sin(angle)), (float) (y - i * 8 * Math.cos(angle)), angle - Math.PI / 2, owner));
						Main.logic.addNewObject(new Bullet((float) (x + i * 8 * Math.sin(angle)), (float) (y + i * 8 * Math.cos(angle)), angle - Math.PI / 2, owner));
						Thread.sleep(burstDelay);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		@Override
		public void applySkill() {
			// TODO Auto-generated method stub
			BulletSpawner beam = new BulletSpawner(pattern);
			beam.start();
			Main.logic.addThreadHolder(beam);
//			BulletSpawner beam = new BulletSpawner(pattern);
//			beam.start();
//			Main.logic.addThreadHolder(beam);
		}
		
	};
	
	public final static PlayerSkill SKILL_3 = new PlayerSkill(15000, IRenderableHolder.enemyAvatar3) {
				
		@Override
		public void applySkill() {
			// TODO Auto-generated method stub
			Player player = Main.logic.getPlayer();
			
			BulletPattern pattern = new BulletPattern(player, 25, 0, 50) {
				
				@Override
				public void spawnBullet() {
					// TODO Auto-generated method stub
					float oldX = player.getX();
					float oldY = player.getY();
					float newX = InputUtility.getMouseX();
					float newY = InputUtility.getMouseY();
					if(PlayerSkill.SKILL_3.isMouseIn){
						newX = new Random().nextFloat() * Configuration.ARENA_WIDTH;
						newY = new Random().nextFloat() * Configuration.ARENA_HEIGHT;
					}
					player.setX(newX);
					player.setY(newY);
					float distantX = (newX - oldX)/wave;
					float distantY = (newY - oldY)/wave;
					double angle = Math.atan2(newY - oldY, newX - oldX);
					try {
						for(int i = 0; i <= wave; i++){
							Main.logic.addNewObject(new Bullet(oldX + i * distantX, oldY + i * distantY, angle + Math.PI / 2, owner));
							Main.logic.addNewObject(new Bullet(oldX + i * distantX, oldY + i * distantY, angle - Math.PI / 2, owner));
							Thread.sleep(burstDelay);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			BulletSpawner blink = new BulletSpawner(pattern);
			blink.start();
			Main.logic.addThreadHolder(blink);
		}
		
	};
	
	public static final PlayerSkill SKILL_4 = new PlayerSkill(300000, IRenderableHolder.playerAvatar) {
		
		@Override
		public void applySkill() {
			// TODO Auto-generated method stub
			Thread buff = new Thread(new Runnable(){
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Player.setBulletPattern(new SpreadPattern(Main.logic.getPlayer(), 5, 30, 1, 0, 50));
						Player.setNewBulletSpawner();
						Thread.sleep(5000);
						Player.setPlayerDefaultPattern();
						Player.setNewBulletSpawner();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			buff.start();
			Main.logic.addThreadHolder(buff);
		}
		
	};
	
	public PlayerSkill(long cd, Image icon){
		super(Configuration.SKILL_ICON_WIDTH, Configuration.SKILL_ICON_WIDTH);
		this.skillCD = cd;
		this.skilIcon = icon;
		this.currentCD = 0;
		
		this.setOnMouseClicked( event -> {			
			System.out.println("cd reset");
			this.useSkill();
		});
		
		this.setOnMouseEntered(event -> {
			this.isMouseIn = true;
			System.out.println("in");
		});
		this.setOnMouseExited(event -> {
			this.isMouseIn = false;
			System.out.println("out");
		});
		
	}
	
	public void useSkill(){
		System.out.println("use skill " + this);
		if(this.currentCD <= 0){
			applySkill();
			this.currentCD = this.skillCD;
		}
		else{
			System.out.println("on cd");
		}
	};
	
	public abstract void applySkill();

	public void render(){
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.drawImage(this.skilIcon, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		if(currentCD > 0){
			gc.setGlobalAlpha(0.5);
			gc.setFill(Color.BLACK);
			gc.fillArc(-this.getWidth()*0.5, -this.getHeight()*0.5, this.getWidth() * 2, this.getHeight() * 2, 90, (currentCD)*360/skillCD, ArcType.ROUND);
			gc.setGlobalAlpha(1);
		}
		
		if(this.currentCD <= 0 && this.isMouseIn){
				gc.setGlobalAlpha(0.5);
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 0, this.getWidth(), this.getHeight());
				gc.setGlobalAlpha(1);
		}
	}
	
	public void setSkillCD(long cd){
		this.skillCD = cd;
	}
	
	public static void updateSkill(){
		if(SKILL_1.currentCD > 0){
			SKILL_1.currentCD -= System.currentTimeMillis() - PlayerSkill.time;
		}
		if(SKILL_2.currentCD > 0){
			SKILL_2.currentCD -= System.currentTimeMillis() - PlayerSkill.time;
		}
		if(SKILL_3.currentCD > 0){
			SKILL_3.currentCD -= System.currentTimeMillis() - PlayerSkill.time;
		}
		if(SKILL_4.currentCD > 0){
			SKILL_4.currentCD -= System.currentTimeMillis() - PlayerSkill.time;
		}
		PlayerSkill.time = System.currentTimeMillis();

//		skill4.update();
	}
}
