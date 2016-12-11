package logic;

import java.util.Random;

import main.Main;
import utilities.Configuration;

public class MobSpawner extends Thread {

	static Random random = new Random();
	static int side;
	
	public MobSpawner() {
		super(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(5000);
						if(Enemy.getEnemyCount() <= 6){
							spawn("rangeDummy");
							spawn("meleeDummy");
							spawn("meleeDummy");
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
				}
			}
			
			public void spawn(String enemy){
				side = randomSide();
				float x = randomX(side);
				float y = randomY(side);
				switch(enemy){
					case "meleeDummy" 	: 	Main.logic.addNewObject(new MeleeDummy(x, y)); break;
					case "rangeDummy" 	: 	Main.logic.addNewObject(new RangedDummy(x, y)); break;
					default	: System.out.println("Invalid enemy"); break;
				}
//				System.out.println("[ " + x + " : " + y + " ]");
			}
			
			public int randomSide(){
				return random.nextInt(5);
			}
			
			public float randomX(int side){
				switch(side){
					case 0	: return Configuration.ARENA_WIDTH + 80;
					case 2 	: return -80;
					default : return random.nextFloat()*Configuration.ARENA_WIDTH; 
				}	
			}
			
			public float randomY(int side){
				switch(side){
					case 1 	: return -80;
					case 3	: return Configuration.ARENA_HEIGHT + 80;
					default : return random.nextFloat()*Configuration.ARENA_HEIGHT; 
				}	
			}
			
			
		});
		// TODO Auto-generated constructor stub
	}

	

}
