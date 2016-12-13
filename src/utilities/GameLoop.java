package utilities;

import bulletSpawner.BulletSpawner;
import graphics.IRenderableHolder;
import gui.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.GameLogic;
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
				if(logic.getPlayer().isDestroy()){
					Main.logic.clearThreadHolder();
					System.out.println("GAME OVER");
					this.stop();
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText(null);
							alert.setContentText("GAME OVER");
							alert.showAndWait();
							try {
								Main.instance.toggleScene();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
				gameScreen.paintComponent();
				logic.logicUpdate();
				IRenderableHolder.getInstance().update();
				InputUtility.postUpdate();
			}
			
			
		}.start();
	}
	
}
