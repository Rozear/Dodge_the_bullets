package logic;

import java.util.Random;

import main.Main;
import utilities.Configuration;

public class MobSpawner extends Thread {

	public MobSpawner() {
		super(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(5000);
						Main.logic.addNewObject(new RangedDummy(new Random().nextFloat() * Configuration.ARENA_WIDTH, new Random().nextFloat() * Configuration.ARENA_HEIGHT));
						Main.logic.addNewObject(new MeleeDummy(new Random().nextFloat() * Configuration.ARENA_WIDTH, new Random().nextFloat() * Configuration.ARENA_HEIGHT));
						Main.logic.addNewObject(new MeleeDummy(new Random().nextFloat() * Configuration.ARENA_WIDTH, new Random().nextFloat() * Configuration.ARENA_HEIGHT));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		// TODO Auto-generated constructor stub
	}

	

}
