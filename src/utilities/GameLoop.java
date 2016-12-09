package utilities;

import javafx.animation.AnimationTimer;
import logic.GameLogic;

public class GameLoop {
	
	GameLogic logic;
	
	public GameLoop(){
		
	}
	
	public void start(){
		new AnimationTimer(){

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				main.Main.instance.getGameScreen().paintComponent();
				GameLogic.logicUpdate();
			}
			
		}.start();
	}
	
}
