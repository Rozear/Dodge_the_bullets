package logic;

public class PlayerBulletSpawner extends BulletSpawner {
	
	public static final PlayerBulletSpawner playerBulletSpawner = new PlayerBulletSpawner();
	static Player player = Player.player;

	public PlayerBulletSpawner() {
		// TODO Auto-generated constructor stub
		super(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(Player.getFiringDelay());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PlayerBulletSpawner.spawnBullet(player.getX(), player.getY(), player.getDirectionX(), player.getDirectionY(), 1, 1);
			}
			
		});
		
	}

	public static void spawnBullet(float x, float y, float directionX, float diractionY, int dmg, int wave) {
		// TODO Auto-generated method stub
		
	}

}
