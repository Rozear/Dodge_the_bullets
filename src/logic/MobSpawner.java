package logic;

import java.util.Random;

import main.Main;
import utilities.Configuration;

public class MobSpawner extends Thread {

	static float randomValue;
	static Random random = new Random();
	
	public MobSpawner() {
		super(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(5000);
						for(int i = 0; i < 4; i++){
							if(Enemy.getEnemyCount() <= 15){
								randomValue = random.nextFloat() * 100;
								if(randomValue <= 25)
									spawn("wisp");
								else if(randomValue <= 50)
									spawn("giant");
								else if(randomValue <= 75)
									spawn("witch");
								else	
									spawn("bandit");
								
							}
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
				}
			}
			
			public void spawn(String enemy){
				int side = random.nextInt(4);
				float x = randomX(side);
				float y = randomY(side);
				switch(enemy){
					case "wisp"			:	Main.logic.addNewObject(new Wisp(x, y));	break;
					case "giant"		:	Main.logic.addNewObject(new Giant(x, y)); 	break;
					case "bandit" 		: 	Main.logic.addNewObject(new Bandit(x, y)); 	break;
					case "witch" 		: 	Main.logic.addNewObject(new Witch(x, y)); 	break;
					default	: System.out.println("Invalid enemy"); break;
				}
				System.out.println("[ " + x + " : " + y + " ]");
			}
			
			public float randomX(int side){
				switch(side){
//					case 0	: return Configuration.ARENA_WIDTH + 80;
//					case 2 	: return -80;
//					default : return random.nextFloat()*Configuration.ARENA_WIDTH; 
				case 0	: return 1200 + 80;
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
