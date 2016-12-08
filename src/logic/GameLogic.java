package logic;

import utilities.Configuration;

public class GameLogic {
		
	public static final Player player = new Player(Configuration.ARENA_WIDTH/2, Configuration.ARENA_HEIGHT/2, 30);

	public static void logicUpdate(){
		player.update();
		System.out.println("logic updated");
	}
}
