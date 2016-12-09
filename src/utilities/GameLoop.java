package utilities;

import gui.GameScreen;
import javafx.animation.AnimationTimer;
import logic.GameLogic;
import main.IRenderableHolder;
import main.Main;

public class GameLoop {
	
	GameScreen gameScreen;
	GameLogic logic;
	
	public GameLoop(GameScreen gameScreen){
		this.gameScreen = gameScreen;
		this.logic = gameScreen.getLogic();
	}
	
	public void start(){
		new AnimationTimer(){

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				gameScreen.paintComponent();
				logic.logicUpdate();
				IRenderableHolder.getInstance().update();
			}
			
			
		}.start();
	}
	
}
