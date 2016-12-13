package bulletSpawner;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import graphics.IRenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import logic.Bullet;
import logic.Player;
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
	
	public final static PlayerSkill SKILL_1 = new PlayerSkill(15000, IRenderableHolder.skill1_icon) {
		
		@Override
		public void applySkill() {
			MediaPlayer mediaPlayer = new MediaPlayer(IRenderableHolder.skill1_sound);
			mediaPlayer.setVolume(0.25);
		    mediaPlayer.play();
			// TODO Auto-generated method stub
			BulletSpawner circle = new BulletSpawner(new SpreadPattern(Main.logic.getPlayer(), 24, 360, 4, 0, 100));
			circle.start();
			Main.logic.addThreadHolder(circle);
		}
		
	};
	
	public final static PlayerSkill SKILL_2 = new PlayerSkill(15000, IRenderableHolder.skill2_icon) {
		
		BulletPattern pattern = new BulletPattern(Main.logic.getPlayer(), 25, 0, 25) {
			
			@Override
			public void spawnBullet() {
				// TODO Auto-generated method stub
				
				Player player = Main.logic.getPlayer();
				float x = player.getX();
				float y = player.getY();
				double angle = player.getAngle();
				try {
					for(int i = 0; i <= wave; i++){
						Main.logic.addNewObject(new Bullet((float) ( x + i * 25 * Math.cos(angle)), (float) ( y + i * 25 * Math.sin(angle)), angle + Math.PI / 2, Bullet.DEFAULT_SPEED, 20, 2, owner));
						Main.logic.addNewObject(new Bullet((float) ( x + i * 25 * Math.cos(angle)), (float) ( y + i * 25 * Math.sin(angle)), angle - Math.PI / 2, Bullet.DEFAULT_SPEED, 20, 2, owner));
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
			MediaPlayer mediaPlayer = new MediaPlayer(IRenderableHolder.skill2_sound);
			mediaPlayer.setVolume(0.25);
		    mediaPlayer.play();
			// TODO Auto-generated method stub
			BulletSpawner beam = new BulletSpawner(pattern);
			beam.start();
			Main.logic.addThreadHolder(beam);
		}
		
	};
	
	public final static PlayerSkill SKILL_3 = new PlayerSkill(15000, IRenderableHolder.skill3_icon) {
				
		@Override
		public void applySkill() {
			// TODO Auto-generated method stub
			MediaPlayer mediaPlayer = new MediaPlayer(IRenderableHolder.skill3_sound);
			mediaPlayer.setVolume(0.25);
		    mediaPlayer.play();
			Player player = Main.logic.getPlayer();
			player.setX(InputUtility.getMouseX());
			player.setY(InputUtility.getMouseY());
			
			BulletPattern pattern = new BulletPattern(player, 1, 0, 20) {
				
				@Override
				public void spawnBullet() {
					for(int i = 0; i < wave; i++){
						for(double angle = 0; angle < Math.PI * 2; angle += Math.PI * 2 / (72)){
								Main.logic.addNewObject(new Bullet(owner.getX(), owner.getY(), angle, Bullet.DEFAULT_SPEED, 5, 1,owner));
						}
					}
				}
				
			};
			
			BulletSpawner blink = new BulletSpawner(pattern);
			blink.start();
			Main.logic.addThreadHolder(blink);
		}
		
	};
	
	public static final PlayerSkill SKILL_4 = new PlayerSkill(30000, IRenderableHolder.skill4_icon) {
		
		@Override
		public void applySkill() {
			
	
			MediaPlayer mediaPlayer = new MediaPlayer(IRenderableHolder.skill4_sound);
			mediaPlayer.setVolume(0.65);
		    mediaPlayer.play();
			// TODO Auto-generated method stub
			Thread buff = new Thread(new Runnable(){
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Player.berserk(true);
						Thread.sleep(5000);
						Player.berserk(false);
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
			this.useSkill();
		});
		
		this.setOnMouseEntered(event -> {
			this.isMouseIn = true;
		});
		this.setOnMouseExited(event -> {
			this.isMouseIn = false;
		});
		
	}
	
	public void useSkill(){
		if(this.currentCD <= 0){
			applySkill();
			this.currentCD = this.skillCD;
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

	}
	public static void resetCD(){
		SKILL_1.currentCD = 0;
		SKILL_2.currentCD = 0;
		SKILL_3.currentCD = 0;
		SKILL_4.currentCD = 0;
	}
		
}
